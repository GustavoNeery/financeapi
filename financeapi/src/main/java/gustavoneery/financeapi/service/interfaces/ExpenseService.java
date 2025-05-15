package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    UUID save(ExpenseDto expenseDto);

    List<ExpenseResponseDto> findAll();

    Expense update(UUID id, ExpenseUpdateDto dto);

    Expense findById(UUID id);

    void delete(UUID id);

    List<ExpenseResponseDto> findByTransactionDate(LocalDate transactionDate);

    UUID replicateExpense(UUID id);

}
