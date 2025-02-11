package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.ExpenseDto;

import java.util.UUID;

public interface ExpenseService {
    public UUID save(ExpenseDto expenseDto);
}
