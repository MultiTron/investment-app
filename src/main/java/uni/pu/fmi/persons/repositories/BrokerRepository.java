package uni.pu.fmi.persons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.models.Broker;

import java.util.UUID;

public interface BrokerRepository extends JpaRepository<Broker, UUID> {
}
