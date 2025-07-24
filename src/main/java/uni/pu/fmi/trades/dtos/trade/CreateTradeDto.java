package uni.pu.fmi.trades.dtos.trade;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDateTime;

public record CreateTradeDto(
        @NotNull(message = "Investor's id can't be null.")
        Long investorId,
        @NotNull(message = "Stock's id can't be null.")
        Long stockId,
        Long brokerId,
        @PositiveOrZero(message = "Shares can't be negative.")
        @NotNull(message = "Shares can't be null.")
        Double shares,
        @NotNull(message = "Timestamp can't be null.")
        LocalDateTime tradeDate) {
}
