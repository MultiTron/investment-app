package uni.pu.fmi.legacy.dtos.broker;

import java.time.LocalDate;

public record GetBrokerDto(String firstName, String lastName, LocalDate birthDate, String licenseNumber, LocalDate brokerSince) {
}
