package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MonthCostService {
    public void save(MonthCostDto monthlyExpensesDto);

    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue);

    public void findMonthCostByExpense(Expense expense);

    public List<MonthCost> findAll();

    public Optional<MonthCost> findByPeriod(LocalDate period);
}
