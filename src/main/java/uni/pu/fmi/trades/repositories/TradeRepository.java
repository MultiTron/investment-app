package uni.pu.fmi.trades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uni.pu.fmi.models.Trade;

public interface TradeRepository extends JpaRepository<Trade, Long> {
}
