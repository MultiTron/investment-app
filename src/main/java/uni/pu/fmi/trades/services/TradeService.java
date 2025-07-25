package uni.pu.fmi.trades.services;

import uni.pu.fmi.trades.dtos.trade.CreateTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetFullTradeDto;
import uni.pu.fmi.trades.dtos.trade.GetTradeDto;
import uni.pu.fmi.trades.dtos.trade.UpdateTradeDto;

import java.util.List;

public interface TradeService {
    List<GetTradeDto> getAll();

    GetTradeDto getById(Long id);

    List<GetFullTradeDto> getAllFull();

    GetFullTradeDto getFullById(Long id);
    Long createTrade(CreateTradeDto createPersonDto);
    void updateTrade(Long id, UpdateTradeDto updatePersonDto);
    void deleteTrade(Long id);
}
