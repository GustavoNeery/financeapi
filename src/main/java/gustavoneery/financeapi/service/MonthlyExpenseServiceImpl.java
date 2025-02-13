package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.MonthlyExpensesDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthlyExpense;
import gustavoneery.financeapi.repository.MonthlyExpenseRepository;
import gustavoneery.financeapi.service.interfaces.MonthlyExpenseService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MonthlyExpenseServiceImpl implements MonthlyExpenseService {

    private MonthlyExpenseRepository monthlyExpenseRepository;

    public UUID save(MonthlyExpensesDto monthlyExpensesDto){
        MonthlyExpense monthlyExpense = new MonthlyExpense();
        monthlyExpense.setMonthYear(monthlyExpensesDto.monthYear());
        monthlyExpense.setTotalSpent(monthlyExpensesDto.totalSpent());

        monthlyExpenseRepository.save(monthlyExpense);
        return monthlyExpense.getId();
    }

    public void verifyExpense(Expense expense) {
        monthlyExpenseRepository.findByMonthYear(expense.getTransactionDate());
    }
}
