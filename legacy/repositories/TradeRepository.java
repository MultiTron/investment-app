package uni.pu.fmi.legacy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.legacy.models.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
