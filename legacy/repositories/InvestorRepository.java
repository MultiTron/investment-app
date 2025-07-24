package uni.pu.fmi.legacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.legacy.models.Investor;

public interface InvestorRepository extends JpaRepository<Investor, Long> {
}
