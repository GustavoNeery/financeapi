package gustavoneery.financeapi.repository;

import gustavoneery.financeapi.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, UUID> {

    @Query(value = "SELECT * FROM expenses e WHERE EXTRACT(MONTH FROM e.transaction_date) = :month", nativeQuery = true)
    List<Expense> findByMonth(@Param("month") int month);
}
