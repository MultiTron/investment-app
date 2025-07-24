package uni.pu.fmi.legacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.legacy.models.Broker;

public interface BrokerRepository extends JpaRepository<Broker, Long> {
}
