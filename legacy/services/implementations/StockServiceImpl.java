package uni.pu.fmi.legacy.services.implementations;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uni.pu.fmi.legacy.dtos.stock.CreateStockDto;
import uni.pu.fmi.legacy.dtos.stock.GetStockDto;
import uni.pu.fmi.legacy.dtos.stock.UpdateStockDto;
import uni.pu.fmi.legacy.mappers.StockMapper;
import uni.pu.fmi.legacy.models.Stock;
import uni.pu.fmi.legacy.repositories.StockRepository;
import uni.pu.fmi.legacy.services.StockService;

import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService {
    private StockMapper mapper;

    public StockServiceImpl(StockMapper mapper, StockRepository repo) {
        this.mapper = mapper;
        this.repo = repo;
    }

    private StockRepository repo;
    @Override
    public List<GetStockDto> getAll() {
        return mapper.toDtoList(repo.findAll());
    }

    @Override
    public GetStockDto getById(Long id) {
        return repo.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public void createStock(CreateStockDto createStockDto) {
        repo.save(mapper.toEntity(createStockDto));
    }

    @Override
    public void updateStock(Long id, UpdateStockDto updateStockDto) {
        Stock stock = repo.findById(id).orElse(null);
        mapper.toEntity(updateStockDto, stock);
    }

    @Override
    public void deleteStock(Long id) {
        repo.deleteById(id);
    }
}
