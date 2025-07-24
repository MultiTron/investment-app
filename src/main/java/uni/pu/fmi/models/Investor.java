package uni.pu.fmi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investors")
@PrimaryKeyJoinColumn(name = "person_id")
public class Investor extends Person {

    @Column(name = "investor_since", nullable = false)
    private LocalDate investorSince;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "watchlists",
            joinColumns = @JoinColumn(name = "investor_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private final List<Stock> watchlist;

    @OneToMany(mappedBy = "investor", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Trade> trades;

    public Investor(String firstName, String lastName, LocalDate birthDate, LocalDate investorSince) {
        super(firstName, lastName, birthDate);
        this.investorSince = investorSince;
        watchlist = new ArrayList<>();
        trades = new ArrayList<>();
    }

    public Investor() {
        super();
        watchlist = new ArrayList<>();
        trades = new ArrayList<>();
    }

    public void addTrade(Trade trade) {
        trades.add(trade);
        trade.setInvestor(this);
    }

    public void removeStock(Trade trade) {
        trades.add(trade);
        trade.setInvestor(null);
    }

    public void addStock(Stock stock) {
        watchlist.add(stock);
        stock.getWatchers().add(this);
    }

    public void removeStock(Stock stock) {
        stock.getWatchers().remove(this);
        watchlist.remove(stock);
    }

    public List<Stock> getWatchlist() {
        return watchlist;
    }

    public List<Trade> getTrades() {
        return trades;
    }

    public LocalDate getInvestorSince() {
        return investorSince;
    }

    public void setInvestorSince(LocalDate investorSince) {
        this.investorSince = investorSince;
    }
}
