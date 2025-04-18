package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.model.Expense;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    UUID save(ExpenseDto expenseDto);

    List<ExpenseResponseWithIdDto> findAll();

    Expense update(UUID id, ExpenseUpdateDto dto);

    void delete(UUID id);

    List<ExpenseResponseWithIdDto> findByTransactionDate(LocalDate transactionDate);

    UUID replicateExpense(UUID id);

}
