package uni.pu.fmi.legacy.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
public class Stock {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String symbol;                        //Proveri go
    @ManyToMany(mappedBy = "watchlist", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Investor> watchers = new ArrayList<>();

    public Stock(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public Stock() {
    }

    public void addInvestor(Investor investor) {
        watchers.add(investor);
        investor.getWatchlist().add(this);
    }

    public void removeInvestor(Investor investor) {
        investor.getWatchlist().remove(this);
        watchers.remove(investor);
    }

    public List<Investor> getWatchers() {
        return watchers;
    }

    public void setWatchers(List<Investor> watchers) {
        this.watchers = watchers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock)) return false;
        return id != null && id.equals(((Stock) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
