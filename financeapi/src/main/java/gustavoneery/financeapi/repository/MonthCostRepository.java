package gustavoneery.financeapi.repository;

import gustavoneery.financeapi.model.MonthCost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface MonthCostRepository extends JpaRepository<MonthCost, UUID> {
    public Optional<MonthCost> findByPeriod(LocalDate period);
}
