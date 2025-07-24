package uni.pu.fmi.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stocks")
@EntityListeners(AuditingEntityListener.class)
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String symbol;
    @ManyToMany(mappedBy = "watchlist", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private final List<Investor> watchers;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private final List<Trade> trades = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDate createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    protected LocalDate updatedAt;

    public Stock(String name, String symbol) {
        this.name = name;
        this.symbol = symbol;
        watchers = new ArrayList<>();
    }

    public Stock() {
        watchers = new ArrayList<>();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stock stock)) return false;
        return id != null && id.equals(stock.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
