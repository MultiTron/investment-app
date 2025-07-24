package uni.pu.fmi.legacy.services;

import uni.pu.fmi.legacy.dtos.stock.CreateStockDto;
import uni.pu.fmi.legacy.dtos.stock.GetStockDto;
import uni.pu.fmi.legacy.dtos.stock.UpdateStockDto;

import java.util.List;

public interface StockService {
    List<GetStockDto> getAll();

    GetStockDto getById(Long id);

    void createStock(CreateStockDto createStockDto);

    void updateStock(Long id, UpdateStockDto updateStockDto);

    void deleteStock(Long id);
}
