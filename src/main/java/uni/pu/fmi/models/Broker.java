package uni.pu.fmi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "brokers")
@PrimaryKeyJoinColumn(name = "person_id")
public class Broker extends Person{

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @Column(name = "broker_since", nullable = false)
    private LocalDate brokerSince;

    public Broker(String firstName, String lastName, LocalDate birthDate, String licenseNumber, LocalDate brokerSince) {
        super(firstName, lastName, birthDate);
        this.licenseNumber = licenseNumber;
        this.brokerSince = brokerSince;
    }

    public Broker() {
        super();
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getBrokerSince() {
        return brokerSince;
    }

    public void setBrokerSince(LocalDate brokerSince) {
        this.brokerSince = brokerSince;
    }
}
