package uni.pu.fmi.trades.dtos.trade;

import java.time.LocalDateTime;

public record GetTradeDto(Long investorId, Long stockId, Long brokerId, Double shares, LocalDateTime tradeDate) {
}
