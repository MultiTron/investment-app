package uni.pu.fmi.stocks.services;

import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;

import java.util.List;

public interface StockService {
    List<GetStockDto> getAll();

    GetStockDto getById(Long id);

    Long createStock(CreateStockDto createStockDto);

    void updateStock(Long id, UpdateStockDto updateStockDto);

    void deleteStock(Long id);
}
