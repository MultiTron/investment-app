package uni.pu.fmi.legacy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "investors")
@PrimaryKeyJoinColumn(name = "person_id")
public class Investor extends Person {

    @Column(name = "investor_since")
    private LocalDate investorSince;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "watchlists",
            joinColumns = @JoinColumn(name = "investor_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id"))
    private List<Stock> watchlist = new ArrayList<>();

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

    public void setWatchlist(List<Stock> watchlist) {
        this.watchlist = watchlist;
    }

    public LocalDate getInvestorSince() {
        return investorSince;
    }

    public void setInvestorSince(LocalDate investorSince) {
        this.investorSince = investorSince;
    }

    public Investor(String firstName, String lastName, LocalDate birthDate, LocalDate investorSince) {
        super(firstName, lastName, birthDate);
        this.investorSince = investorSince;
    }

    public Investor() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Investor)) return false;
        return id != null && id.equals(((Investor) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
