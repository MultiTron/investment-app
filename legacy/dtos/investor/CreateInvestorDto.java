package uni.pu.fmi.legacy.dtos.investor;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
public record CreateInvestorDto(@NotEmpty
                                @Size(min = 2)
                                String firstName,
                                @NotEmpty
                                @Size(min = 2)
                                String lastName,
                                @PastOrPresent
                                LocalDate birthDate,
                                @PastOrPresent
                                LocalDate investorSince) {
}

