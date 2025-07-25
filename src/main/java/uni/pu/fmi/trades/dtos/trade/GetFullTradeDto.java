package uni.pu.fmi.trades.dtos.trade;

import uni.pu.fmi.persons.dtos.broker.GetBrokerDto;
import uni.pu.fmi.persons.dtos.investors.GetInvestorDto;
import uni.pu.fmi.stocks.dtos.stock.GetStockDto;

import java.time.LocalDateTime;

public record GetFullTradeDto(GetInvestorDto investor, GetStockDto stock, GetBrokerDto broker, Double shares, LocalDateTime tradeDate) {
}
