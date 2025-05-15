package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.MonthCost;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MonthCostService {
    public void save(MonthCostDto monthlyExpensesDto);

    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue);

    public Optional<MonthCost> findByPeriod(LocalDate period);

    public List<MonthCost> findAll();

    public void delete(UUID id);
}
