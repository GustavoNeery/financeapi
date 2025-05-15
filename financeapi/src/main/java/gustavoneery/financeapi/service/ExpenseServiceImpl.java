package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.exceptions.ExpenseNotFoundException;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;
import gustavoneery.financeapi.repository.ExpenseRepository;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import gustavoneery.financeapi.service.interfaces.MonthCostService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseRepository expenseRepository;
    private MonthCostService monthCostService;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository, MonthCostService monthCostService) {
        this.expenseRepository = expenseRepository;
        this.monthCostService = monthCostService;
    }

    @Override
    public UUID save(ExpenseDto expenseDto){
        Expense expense = new Expense(expenseDto);
        expenseRepository.save(expense);
        this.checkIfCreateOrUpdateMonthCost(expense);

        return expense.getId();
    }

    private void checkIfCreateOrUpdateMonthCost(Expense expense) {
        Optional<MonthCost> monthCostOptional = monthCostService.findByPeriod(expense.getTransactionDate());

        if(monthCostOptional.isPresent()) {
            monthCostService.updateTotalSpent(monthCostOptional.get(), expense.getPurchaseValue());
        } else {
            monthCostService.save(new MonthCostDto(expense.getTransactionDate(), expense.getPurchaseValue()));
        }
    }

    @Override
    public Expense findById(UUID id) {
        return expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
    }

    @Override
    public UUID replicateExpense(UUID id){
        final Expense expense = findById(id);
        Expense newExpense = new Expense();

        newExpense.setName(expense.getName());
        newExpense.setTransactionDate(replicateDateToNextMonth(expense));
        newExpense.setInstallmentsCount(expense.getInstallmentsCount());
        newExpense.setCategory(expense.getCategory());
        newExpense.setCreatedAt(expense.getCreatedAt().plusMonths(1));
        newExpense.setUpdatedAt(LocalDateTime.now());
        newExpense.setFixedExpense(expense.getFixedExpense());
        newExpense.setPurchaseValue(expense.getPurchaseValue());

        expenseRepository.save(newExpense);
        this.checkIfCreateOrUpdateMonthCost(newExpense);

        return newExpense.getId();
    }

    private LocalDate replicateDateToNextMonth(Expense expense) {
        return expense.getTransactionDate().plusMonths(1);
    }

    @Override
    public List<ExpenseResponseDto> findAll(){
        return expenseRepository.findAll()
                .stream()
                .map(ExpenseResponseDto::new)
                .toList();
    }

    @Override
    public Expense update(UUID id, ExpenseUpdateDto expenseDto){
        Expense expense = findById(id);
        Expense expenseUpdated = updateNonNullFields(expenseDto, expense);

        return expenseRepository.save(expenseUpdated);
    }

    @Override
    public void delete(UUID id) {
        Expense expense = findById(id);

        expense.setPurchaseValue(-expense.getPurchaseValue());
        this.checkIfCreateOrUpdateMonthCost(expense);

        expenseRepository.deleteById(id);
    }

    @Override
    public List<ExpenseResponseDto> findByTransactionDate(LocalDate transactionDate) {
        List<Expense> expenses = expenseRepository.findByMonth(transactionDate.getMonth().getValue(), transactionDate.getYear());

        if(expenses.isEmpty()) {
            throw new ExpenseNotFoundException("Expenses with transaction date: "+ transactionDate + " not found");
        }

        return expenses.stream().map(ExpenseResponseDto::new).toList();
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
