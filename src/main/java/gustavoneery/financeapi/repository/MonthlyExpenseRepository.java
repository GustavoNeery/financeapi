package gustavoneery.financeapi.repository;

import gustavoneery.financeapi.model.MonthlyExpense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MonthlyExpenseRepository extends JpaRepository<MonthlyExpense, UUID> {
//    public Optional<MonthlyExpense> findByDate(YearMonth yearMonth);
}
