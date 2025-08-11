package uni.pu.fmi.trades.services;

import uni.pu.fmi.trades.dtos.trade.CreateTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetFullTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetTradeDto;
import uni.pu.fmi.trades.dtos.trade.UpdateTradeDto;

import java.util.List;
import java.util.UUID;

public interface TradeService {
    List<GetTradeDto> getAll();

    GetTradeDto getById(UUID id);

    List<GetFullTradeDto> getAllFull();

    GetFullTradeDto getFullById(UUID id);
    UUID createTrade(CreateTradeDto createPersonDto);
    void updateTrade(UUID id, UpdateTradeDto updatePersonDto);
    void deleteTrade(UUID id);
}
