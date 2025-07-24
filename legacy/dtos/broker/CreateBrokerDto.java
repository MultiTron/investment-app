package uni.pu.fmi.legacy.dtos.broker;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateBrokerDto(
        @NotEmpty
        @Size(min = 2)
        String firstName,
        @NotEmpty
        @Size(min = 2)
        String lastName,
        @PastOrPresent
        LocalDate birthDate,
        @NotEmpty
        @Size(min = 10, max = 10)
        String licenseNumber,
        LocalDate brokerSince) {
}
