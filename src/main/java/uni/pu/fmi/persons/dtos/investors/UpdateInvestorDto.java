package uni.pu.fmi.persons.dtos.investors;

import java.time.LocalDate;

public record UpdateInvestorDto(
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate investorSince) {
}
