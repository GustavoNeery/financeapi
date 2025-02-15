package gustavoneery.financeapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "month_costs")
@Entity
public class MonthCost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "period", nullable = false)
    private LocalDate period;

    @Column(name = "total_spent", nullable = false)
    private Double totalSpent;

    public MonthCost() {
    }

    public MonthCost(LocalDate period, Double totalSpent) {
        this.period = period;
        this.totalSpent = totalSpent;
    }

    public void setPeriod(LocalDate period) {
        this.period = period;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getPeriod() {
        return period;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }
}
