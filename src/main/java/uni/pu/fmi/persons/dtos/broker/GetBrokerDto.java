package uni.pu.fmi.persons.dtos.broker;

import java.time.LocalDate;

public record GetBrokerDto(String firstName, String lastName, LocalDate birthDate, String licenseNumber, LocalDate brokerSince) {
}
