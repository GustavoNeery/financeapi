package gustavoneery.financeapi.model;

import gustavoneery.financeapi.dto.ExpenseDto;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column(name = "transaction_date")
    private LocalDate transactionDate;

    @Column(name = "purchase_value")
    private Double purchaseValue;

    @Column(name = "installments_count")
    private int installmentsCount;

    @Column
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "fixed_expense")
    private boolean fixedExpense;

    public Expense(){

    }

    public Expense(String name, LocalDate transactionDate, Double purchaseValue, int installmentsCount, String category, LocalDateTime createdAt, LocalDateTime updatedAt, boolean fixedExpense) {
        this();
        this.name = name;
        this.transactionDate = transactionDate;
        this.installmentsCount = installmentsCount;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.fixedExpense = fixedExpense;
        this.purchaseValue = purchaseValue;
    }

    public Expense(ExpenseDto expenseDto){
        this(expenseDto.name(), expenseDto.transactionDate().withDayOfMonth(1), expenseDto.purchaseValue(), expenseDto.installmentsCount(), expenseDto.category(), expenseDto.createdAt(), expenseDto.updatedAt(), expenseDto.fixedExpense());
    }

    public void setFixedExpense(boolean fixedExpense) {
        this.fixedExpense = fixedExpense;
    }

    public boolean getFixedExpense() {
        return fixedExpense;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setPurchaseValue(Double purchaseValue) {
        this.purchaseValue = purchaseValue;
    }

    public Double getPurchaseValue() {
        return purchaseValue;
    }

    public void setInstallmentsCount(int installmentsCount) {
        this.installmentsCount = installmentsCount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public int getInstallmentsCount() {
        return installmentsCount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transactionDate=" + transactionDate +
                ", purchaseValue=" + purchaseValue +
                ", installmentsCount=" + installmentsCount +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", fixedExpense=" + fixedExpense +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Expense)) return false;
        Expense expense = (Expense) o;
        return Double.compare(expense.purchaseValue, purchaseValue) == 0 &&
                Objects.equals(name, expense.name) &&
                Objects.equals(transactionDate, expense.transactionDate) &&
                Objects.equals(category, expense.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, transactionDate, purchaseValue, category);
    }
}
