package uni.pu.fmi.stocks.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.exceptions.ResourceNotFoundException;
import uni.pu.fmi.models.Stock;
import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;
import uni.pu.fmi.stocks.mappers.StockMapper;
import uni.pu.fmi.stocks.repositories.StockRepository;
import uni.pu.fmi.stocks.services.StockService;

import java.util.List;

import static uni.pu.fmi.exceptions.ErrorConstants.stockNotFound;

@Service
@Transactional
public class StockServiceImpl implements StockService {
    private final StockRepository repo;
    private final StockMapper mapper;

    public StockServiceImpl(StockMapper mapper, StockRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }
    @Override
    public List<GetStockDto> getAll() {
        return mapper.toDtoList(repo.findAll());
    }

    @Override
    public GetStockDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException(stockNotFound(id)));
    }

    @Override
    public Long createStock(CreateStockDto createStockDto) {
        return repo.save(mapper.toEntity(createStockDto)).getId();
    }

    @Override
    public void updateStock(Long id, UpdateStockDto updateStockDto) {
        Stock stock = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(stockNotFound(id)));
        mapper.toEntity(updateStockDto, stock);
    }

    @Override
    public void deleteStock(Long id) {
        if (!repo.existsById(id)){
            throw new ResourceNotFoundException(stockNotFound(id));
        }
        repo.deleteById(id);
    }
}
