package uni.pu.fmi.trades.dtos.trade;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateTradeDto(
        UUID investorId,
        UUID stockId,
        UUID brokerId,
        Double shares,
        LocalDateTime tradeDate) {
}
