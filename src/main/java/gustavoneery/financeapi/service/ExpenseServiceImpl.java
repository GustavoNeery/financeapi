package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.repository.ExpenseRepository;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import gustavoneery.financeapi.service.interfaces.MonthlyExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private MonthlyExpenseService monthlyExpenseService;

    public UUID save(ExpenseDto expenseDto){
        Expense expense = new Expense();
        expense.setCategory(expenseDto.category());
        expense.setName(expenseDto.name());
        expense.setInstallmentsCount(expenseDto.installmentsCount());
        expense.setTransactionDate(expenseDto.transactionDate());
        expense.setPurchaseValue(expenseDto.purchaseValue());
        expense.setCreatedAt(LocalDateTime.now());
        expenseRepository.save(expense);
//        monthlyExpenseService.verifyExpense(expense);

        return expense.getId();
    }

    public List<ExpenseResponseWithIdDto> findAllWithId(){
        return expenseRepository.findAll()
                .stream()
                .map(expense -> new ExpenseResponseWithIdDto(expense.getId(),
                expense.getName(),
                expense.getPurchaseValue(),
                expense.getTransactionDate(),
                expense.getInstallmentsCount(),
                expense.getCategory(),
                expense.getCreatedAt(),
                expense.getUpdatedAt())).toList();
    }

    public List<ExpenseResponseDto> findAll(){
        return expenseRepository.findAll()
                .stream()
                .map(expense -> new ExpenseResponseDto(
                        expense.getName(),
                        expense.getPurchaseValue(),
                        expense.getTransactionDate(),
                        expense.getInstallmentsCount(),
                        expense.getCategory())).toList();
    }
}
