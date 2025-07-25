package uni.pu.fmi.trades.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uni.pu.fmi.models.Trade;

import java.util.List;
import java.util.Optional;

public interface TradeRepository extends JpaRepository<Trade, Long> {

    @Query("""
            SELECT t FROM Trade t
            JOIN FETCH Investor i ON t.investor = i
            LEFT JOIN FETCH Broker b ON t.broker = b
            JOIN FETCH Stock s ON t.stock = s
            """)
    public List<Trade> findAllFull();

    @Query("""
            SELECT t FROM Trade t
            JOIN FETCH Investor i ON t.investor = i
            LEFT JOIN FETCH Broker b ON t.broker = b
            JOIN FETCH Stock s ON t.stock = s
            WHERE t.id = :id
            """)
    public Optional<Trade> findByIdFull(Long id);
}
