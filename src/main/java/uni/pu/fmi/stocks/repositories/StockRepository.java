package uni.pu.fmi.stocks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uni.pu.fmi.models.Stock;

import java.util.List;
import java.util.UUID;

@Repository
public interface StockRepository extends JpaRepository<Stock, UUID> {

    @Query("""
    SELECT s FROM Investor i
    JOIN i.watchlist s
    WHERE i.id = :investorId
    ORDER BY s.createdAt ASC
""")
    public List<Stock> findAllByInvestorId(@Param("investorId") UUID investorId);
}
