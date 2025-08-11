package uni.pu.fmi.stocks.services;

import uni.pu.fmi.stocks.dtos.stock.CreateStockDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;
import uni.pu.fmi.stocks.dtos.stock.UpdateStockDto;

import java.util.List;
import java.util.UUID;

public interface StockService {
    List<GetStockDto> getAll();

    GetStockDto getById(UUID id);

    UUID createStock(CreateStockDto createStockDto);

    void updateStock(UUID id, UpdateStockDto updateStockDto);

    void deleteStock(UUID id);
}
