package uni.pu.fmi.persons.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Investor;
import uni.pu.fmi.models.Stock;
import uni.pu.fmi.persons.dtos.investors.CreateInvestorDto;
import uni.pu.fmi.persons.dtos.investors.GetInvestorDto;
import uni.pu.fmi.persons.dtos.investors.UpdateInvestorDto;
import uni.pu.fmi.persons.mappers.InvestorMapper;
import uni.pu.fmi.persons.repositories.InvestorRepository;
import uni.pu.fmi.persons.services.PersonService;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.mappers.StockMapper;
import uni.pu.fmi.stocks.repositories.StockRepository;

import java.util.List;
import java.util.UUID;

import static uni.pu.fmi.exceptions.ErrorConstants.investorNotFound;
import static uni.pu.fmi.exceptions.ErrorConstants.stockNotFound;

@Service
@Transactional
public class InvestorServiceImpl implements PersonService<GetInvestorDto, CreateInvestorDto, UpdateInvestorDto> {
    private final InvestorMapper invMapper;
    private final StockMapper stockMapper;
    private final InvestorRepository invRepo;
    private final StockRepository stockRepo;

    public InvestorServiceImpl(InvestorRepository invRepo, StockRepository stockRepo, InvestorMapper mapper, StockMapper stockMapper) {
        this.invRepo = invRepo;
        this.stockRepo = stockRepo;
        this.invMapper = mapper;
        this.stockMapper = stockMapper;
    }

    @Override
    public List<GetInvestorDto> getAll() {
        return invMapper.toDtoList(invRepo.findAll());
    }

    @Override
    public GetInvestorDto getById(UUID id) {
        return invRepo.findById(id).map(invMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(investorNotFound(id)));
    }

    @Override
    public UUID createPerson(CreateInvestorDto createPersonDto) {
        return invRepo.save(invMapper.toEntity(createPersonDto)).getId();
    }

    @Override
    public void updatePerson(UUID id, UpdateInvestorDto updatePersonDto) {
        Investor investor = invRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(investorNotFound(id)));
        invMapper.toEntity(updatePersonDto, investor);
    }

    @Override
    public void deletePerson(UUID id) {
        if (!invRepo.existsById(id)){
            throw new ResourceNotFoundException(investorNotFound(id));
        }
        invRepo.deleteById(id);
    }

    public List<GetStockDto> getWatchlist(UUID id){
        var investor = invRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(investorNotFound(id)));
        return stockMapper.toDtoList(investor.getWatchlist());
    }

    public void addToWatchlist(UUID investorId, UUID stockId){
        Stock stock = stockRepo.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException(stockNotFound(stockId)));
        Investor investor = invRepo.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException(investorNotFound(investorId)));
        investor.addStock(stock);
    }

    public void removeFromWatchlist(UUID investorId, UUID stockId){
        Stock stock = stockRepo.findById(stockId)
                .orElseThrow(() -> new ResourceNotFoundException(stockNotFound(stockId)));
        Investor investor = invRepo.findById(investorId)
                .orElseThrow(() -> new ResourceNotFoundException(investorNotFound(investorId)));
        investor.removeStock(stock);
    }
}
