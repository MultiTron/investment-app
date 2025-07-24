package uni.pu.fmi.legacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.legacy.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {
}
