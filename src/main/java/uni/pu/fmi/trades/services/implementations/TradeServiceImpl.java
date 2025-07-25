package uni.pu.fmi.trades.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Trade;
import uni.pu.fmi.trades.dtos.trade.CreateTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetFullTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetTradeDto;
import uni.pu.fmi.trades.dtos.trade.UpdateTradeDto;
import uni.pu.fmi.trades.mappers.TradeMapper;
import uni.pu.fmi.trades.repositories.TradeRepository;
import uni.pu.fmi.trades.services.TradeService;

import java.util.List;

import static uni.pu.fmi.exceptions.ErrorConstants.tradeNotFound;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

    private final TradeRepository repo;
    private final TradeMapper mapper;

    public TradeServiceImpl(TradeRepository repo, TradeMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public List<GetTradeDto> getAll() {
        return mapper.toDtoList(repo.findAll());
    }

    @Override
    public GetTradeDto getById(Long id) {
        return mapper.toDto(repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(tradeNotFound(id))));
    }

    @Override
    public List<GetFullTradeDto> getAllFull() {
        return mapper.toFullDtoList(repo.findAllFull());
    }

    @Override
    public GetFullTradeDto getFullById(Long id) {
        return mapper.toFullDto(repo.findByIdFull(id)
                .orElseThrow(() -> new ResourceNotFoundException(tradeNotFound(id))));
    }

    @Override
    public Long createTrade(CreateTradeDto createTradeDto) {
        return repo.save(mapper.toEntity(createTradeDto)).getId();
    }

    @Override
    public void updateTrade(Long id, UpdateTradeDto updateTradeDto) {
        Trade trade = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(tradeNotFound(id)));
        mapper.toEntity(updateTradeDto, trade);
    }

    @Override
    public void deleteTrade(Long id) {
        if (!repo.existsById(id)){
            throw new ResourceNotFoundException(tradeNotFound(id));
        }
        repo.deleteById(id);
    }
}
