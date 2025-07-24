package uni.pu.fmi.legacy.dtos.investor;

import java.time.LocalDate;

public record GetInvestorDto(String firstName, String lastName, LocalDate birthDate, LocalDate investorSince) {
}
