package gustavoneery.financeapi.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Table(name = "monthly_expenses")
@Entity
public class MonthlyExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "month_year", nullable = false)
    private LocalDate monthYear;

    @Column(name = "total_spent", nullable = false)
    private Double totalSpent;

    public MonthlyExpense() {
    }

    public MonthlyExpense(LocalDate monthYear, Double totalSpent) {
        this.monthYear = monthYear;
        this.totalSpent = totalSpent;
    }

    public void setMonthYear(LocalDate monthYear) {
        this.monthYear = monthYear;
    }

    public void setTotalSpent(Double totalSpent) {
        this.totalSpent = totalSpent;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getMonthYear() {
        return monthYear;
    }

    public Double getTotalSpent() {
        return totalSpent;
    }
}
