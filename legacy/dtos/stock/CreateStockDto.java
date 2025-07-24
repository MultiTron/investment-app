package uni.pu.fmi.legacy.dtos.stock;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record CreateStockDto(@NotEmpty
                             @Size(min = 2)
                             String name,
                             @NotEmpty
                             @Size(min = 3, max = 3)
                             String symbol) {

}

