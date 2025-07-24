package uni.pu.fmi.persons.dtos.broker;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateBrokerDto(
        @NotEmpty(message = "First name can't be empty.")
        @Size(min = 2, message = "First name must be longer than 2 characters")
        String firstName,
        @NotEmpty(message = "Last name can't be empty.")
        @Size(min = 2, message = "Last name must be longer than 2 characters.")
        String lastName,
        @PastOrPresent(message = "Birth date can't be in the future.")
        LocalDate birthDate,
        @NotEmpty(message = "License number can't be empty.")
        @Pattern(regexp="^(\\d*)$", message = "License must contain only digits.")
        @Size(min = 10, max = 10, message = "License number must be 10 digits.")
        String licenseNumber,
        @PastOrPresent(message = "Broker since can't be in the future.")
        LocalDate brokerSince) {
}
