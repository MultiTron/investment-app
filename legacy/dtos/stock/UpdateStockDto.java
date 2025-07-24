package uni.pu.fmi.legacy.dtos.stock;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record UpdateStockDto(
        String name,
        String symbol) {
}
