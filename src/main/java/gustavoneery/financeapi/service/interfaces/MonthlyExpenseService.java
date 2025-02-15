package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthlyExpensesDto;
import gustavoneery.financeapi.model.Expense;

import java.util.UUID;

public interface MonthlyExpenseService {
    public UUID save(MonthlyExpensesDto monthlyExpensesDto);

    public void verifyMonthlyExpenseByExpense(Expense expense);
}
