package uni.pu.fmi.persons.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.models.Investor;

import java.util.UUID;

public interface InvestorRepository extends JpaRepository<Investor, UUID> {
}
