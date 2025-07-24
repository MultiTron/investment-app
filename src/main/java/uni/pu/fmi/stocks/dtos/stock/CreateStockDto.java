package uni.pu.fmi.stocks.dtos.stock;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


public record CreateStockDto(
        @NotEmpty(message = "Stock name can't be empty.")
        @Size(min = 2, message = "Stock name must be longer then to 2 characters.")
        String name,
        @NotEmpty(message = "Stock symbol can't be empty.")
        @Size(min = 3, max = 4, message = "Stock name must be between 3 and 4 characters long.")
        String symbol) {

}

