package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;

public interface MonthCostService {
    public void save(MonthCostDto monthlyExpensesDto);

    public void update(MonthCost monthCost, Double purchaseValue);

    public void findMonthCostByExpense(Expense expense);
}
