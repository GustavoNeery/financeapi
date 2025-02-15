package gustavoneery.financeapi.model;

import jakarta.persistence.*;

import java.time.YearMonth;
import java.util.UUID;

@Table(name = "monthly_expenses")
@Entity
public class MonthlyExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "year_month", nullable = false)
    private YearMonth yearMonth;

    @Column(name = "total_spent", nullable = false)
    private Double totalSpent;

    public MonthlyExpense() {
    }

    public MonthlyExpense(YearMonth yearMonth, Double totalSpent) {
        this.yearMonth = yearMonth;
        this.totalSpent = totalSpent;
    }

    public void setYearMonth(YearMonth yearMonth) {
        this.yearMonth = this.yearMonth;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public UUID getId() {
        return id;
    }

    public YearMonth getYearMonth() {
        return yearMonth;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }
}
