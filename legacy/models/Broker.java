package uni.pu.fmi.legacy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "brokers")
@PrimaryKeyJoinColumn(name = "person_id")
public class Broker extends Person{

    @Column(name = "license_number")
    private String licenseNumber;

    @Column(name = "broker_since")
    private LocalDate brokerSince;

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

    public Broker(String firstName, String lastName, LocalDate birthDate, String licenseNumber, LocalDate brokerSince) {
        super(firstName, lastName, birthDate);
        this.licenseNumber = licenseNumber;
        this.brokerSince = brokerSince;
    }

    public Broker() {
        super();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Broker)) return false;
        return id != null && id.equals(((Broker) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
