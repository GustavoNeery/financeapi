package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.exceptions.ExpenseNotFoundException;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.enums.Operation;
import gustavoneery.financeapi.repository.ExpenseRepository;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import gustavoneery.financeapi.service.interfaces.MonthCostService;
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
    private MonthCostService monthCostService;

    @Override
    public UUID save(ExpenseDto expenseDto){
        Expense expense = new Expense();
        expense.setCategory(expenseDto.category());
        expense.setName(expenseDto.name());
        expense.setInstallmentsCount(expenseDto.installmentsCount());
        expense.setTransactionDate(expenseDto.transactionDate());
        expense.setPurchaseValue(expenseDto.purchaseValue());
        expense.setCreatedAt(LocalDateTime.now());
        expenseRepository.save(expense);
        monthCostService.findMonthCostByExpense(expense, Operation.ADD);

        return expense.getId();
    }

    @Override
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

    @Override
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

    @Override
    public Expense update(UUID id, ExpenseUpdateDto expenseDto){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        Expense expenseUpdated = updateNonNullFields(expenseDto, expense);

        return expenseRepository.save(expenseUpdated);
    }

    public void delete(UUID id) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        monthCostService.findMonthCostByExpense(expense, Operation.SUBTRACT);
        expenseRepository.deleteById(id);
    }

    private Expense updateNonNullFields(ExpenseUpdateDto expenseDto, Expense expense) {
        if(expenseDto.name() != null) {
            expense.setName(expenseDto.name());
        }

        if(expenseDto.purchaseValue() != null) {
            expense.setPurchaseValue(expenseDto.purchaseValue());
        }

        if(expenseDto.transactionDate() != null) {
            expense.setTransactionDate(expenseDto.transactionDate());
        }

        if(expenseDto.category() != null) {
            expense.setCategory(expenseDto.category());
        }

        return expense;
    }
}
