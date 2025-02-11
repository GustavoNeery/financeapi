package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService{

    @Autowired
    private ExpenseRepository expenseRepository;

    public UUID save(ExpenseDto expenseDto){
        Expense expense = new Expense();
        expense.setCategory(expenseDto.category());
        expense.setName(expenseDto.name());
        expense.setInstallmentsCount(expenseDto.installmentsCount());
        expense.setTransactionDate(expenseDto.transactionDate());
        expense.setPurchaseValue(expenseDto.purchaseValue());
        expense.setCreatedAt(LocalDateTime.now());
        expenseRepository.save(expense);

        return expense.getId();
    }
}
