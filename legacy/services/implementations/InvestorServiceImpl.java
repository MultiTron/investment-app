package uni.pu.fmi.legacy.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.legacy.dtos.investor.CreateInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.GetInvestorDto;
import uni.pu.fmi.legacy.dtos.investor.UpdateInvestorDto;
import uni.pu.fmi.legacy.mappers.InvestorMapper;
import uni.pu.fmi.legacy.models.Investor;
import uni.pu.fmi.legacy.models.Stock;
import uni.pu.fmi.legacy.repositories.InvestorRepository;
import uni.pu.fmi.legacy.repositories.StockRepository;
import uni.pu.fmi.legacy.services.PersonService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InvestorServiceImpl implements PersonService<GetInvestorDto, CreateInvestorDto, UpdateInvestorDto> {
    private InvestorMapper mapper;
    private InvestorRepository invRepo;
    private StockRepository stockRepo;

    public InvestorServiceImpl(InvestorRepository invRepo, StockRepository stockRepo, InvestorMapper mapper) {
        this.invRepo = invRepo;
        this.stockRepo = stockRepo;
        this.mapper = mapper;
    }

    @Override
    public List<GetInvestorDto> getAll() {
        return mapper.toDtoList(invRepo.findAll());
    }

    @Override
    public GetInvestorDto getById(Long id) {
        Optional<GetInvestorDto> optional = invRepo.findById(id).map(mapper::toDto);
        return optional.orElse(null);
    }

    @Override
    public void createPerson(CreateInvestorDto createPersonDto) {
        invRepo.save(mapper.toEntity(createPersonDto));
    }

    @Override
    public void updatePerson(Long id, UpdateInvestorDto updatePersonDto) {
        Investor investor = invRepo.findById(id).orElse(null);
        mapper.toEntity(updatePersonDto, investor);
    }

    @Override
    public void deletePerson(Long id) {
        invRepo.deleteById(id);
    }

    public void addToWatchlist(Long investorId, Long stockId){
        Stock stock = stockRepo.findById(stockId).orElse(null);
        Investor investor = invRepo.findById(investorId).orElse(null);
        if (stock == null || investor == null){
            //TODO throw exception
        }
        investor.addStock(stock);
    }

    public void removeFromWatchlist(Long investorId, Long stockId){
        Stock stock = stockRepo.findById(stockId).orElse(null);
        Investor investor = invRepo.findById(investorId).orElse(null);
        if (stock == null || investor == null){
            //TODO throw exception
        }
        investor.removeStock(stock);
    }
}
