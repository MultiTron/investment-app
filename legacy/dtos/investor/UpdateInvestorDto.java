package uni.pu.fmi.legacy.dtos.investor;

import java.time.LocalDate;

public record UpdateInvestorDto(
        String firstName,
        String lastName,
        LocalDate birthDate,
        LocalDate investorSince) {
}
