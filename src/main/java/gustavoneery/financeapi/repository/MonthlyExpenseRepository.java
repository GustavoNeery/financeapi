package gustavoneery.financeapi.repository;

import gustavoneery.financeapi.model.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface MonthlyExpenseRepository extends JpaRepository<MonthlyExpense, UUID> {
    public MonthlyExpense findByMonthYear(LocalDate monthYear);
}
