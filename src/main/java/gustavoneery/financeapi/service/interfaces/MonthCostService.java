package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;

import java.time.LocalDate;
import java.util.List;

public interface MonthCostService {
    public void save(MonthCostDto monthlyExpensesDto);

    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue);

    public void findMonthCostByExpense(Expense expense);

    public List<MonthCost> findAll();

    public MonthCost findByPeriod(LocalDate period);
}
