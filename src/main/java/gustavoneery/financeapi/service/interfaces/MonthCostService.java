package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;
import gustavoneery.financeapi.model.enums.Operation;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface MonthCostService {
    public void save(MonthCostDto monthlyExpensesDto);

    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue, Operation operation);

    public void findMonthCostByExpense(Expense expense, Operation operation);

    public List<MonthCost> findAll();

    public MonthCost findByPeriod(LocalDate period);

    public void delete(UUID id);
}
