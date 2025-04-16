package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.exceptions.ExpenseNotFoundException;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.enums.Operation;
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
        Expense expense = new Expense();
        expense.setCategory(expenseDto.category());
        expense.setName(expenseDto.name());
        expense.setInstallmentsCount(expenseDto.installmentsCount());
        expense.setTransactionDate(expenseDto.transactionDate());
        expense.setPurchaseValue(expenseDto.purchaseValue());
        expense.setCreatedAt(LocalDateTime.now());
        expense.setFixedExpense(expenseDto.fixedExpense());
        expenseRepository.save(expense);
        monthCostService.findByExpense(expense, Operation.ADD);

        return expense.getId();
    }

    @Override
    public UUID replicateExpense(UUID id){
        final Optional<Expense> expenseOptional = expenseRepository.findById(id);

        if(expenseOptional.isEmpty()){
            throw new ExpenseNotFoundException("Expense not found");
        }

        final Expense expense = expenseOptional.get();
        final LocalDate newTransactionDate = replicateDateToNextMonth(expense);

        Expense newExpense = new Expense();
        newExpense.setCategory(expense.getCategory());
        newExpense.setName(expense.getName());
        newExpense.setInstallmentsCount(expense.getInstallmentsCount());
        newExpense.setTransactionDate(newTransactionDate);
        newExpense.setCreatedAt(expense.getCreatedAt().plusMonths(1));
        newExpense.setPurchaseValue(expense.getPurchaseValue());
        newExpense.setFixedExpense(expense.getFixedExpense());

        expenseRepository.save(newExpense);
        monthCostService.findByExpense(newExpense, Operation.ADD);

        return newExpense.getId();

    }

    private LocalDate replicateDateToNextMonth(Expense expense) {
        return expense.getTransactionDate().plusMonths(1);
    }

    @Override
    public List<ExpenseResponseWithIdDto> findAll(){
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
    public Expense update(UUID id, ExpenseUpdateDto expenseDto){
        Expense expense = expenseRepository.findById(id).orElseThrow(() -> new ExpenseNotFoundException("Expense not found"));
        Expense expenseUpdated = updateNonNullFields(expenseDto, expense);

        return expenseRepository.save(expenseUpdated);
    }

    @Override
    public void delete(UUID id) {
        Expense expense = expenseRepository.findById(id).orElseThrow();
        monthCostService.findByExpense(expense, Operation.SUBTRACT);
        expenseRepository.deleteById(id);
    }

    @Override
    public List<ExpenseResponseWithIdDto> findByTransactionDate(LocalDate transactionDate) {
        return expenseRepository.findByMonth(transactionDate.getMonth().getValue()).stream().map(expense -> new ExpenseResponseWithIdDto(
                expense.getId(),
                expense.getName(),
                expense.getPurchaseValue(),
                expense.getTransactionDate(),
                expense.getInstallmentsCount(),
                expense.getCategory(),
                expense.getCreatedAt(),
                expense.getUpdatedAt())).toList();
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
