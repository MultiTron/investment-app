package uni.pu.fmi.persons.dtos.investors;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateInvestorDto(
        @NotEmpty(message = "First name can't be empty.")
        @Size(min = 2, message = "First name must be longer than 2 characters")
        String firstName,
        @NotEmpty(message = "Last name can't be empty.")
        @Size(min = 2, message = "Last name must be longer than 2 characters.")
        String lastName,
        @PastOrPresent(message = "Birth date can't be in the future.")
        LocalDate birthDate,
        @PastOrPresent(message = "Investor since can't be in the future.")
        LocalDate investorSince) {
}

