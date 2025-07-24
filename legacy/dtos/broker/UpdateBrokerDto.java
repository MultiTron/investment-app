package uni.pu.fmi.legacy.dtos.broker;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record UpdateBrokerDto(
        String firstName,
        String lastName,
        LocalDate birthDate,
        String licenseNumber,
        LocalDate brokerSince) {
}
