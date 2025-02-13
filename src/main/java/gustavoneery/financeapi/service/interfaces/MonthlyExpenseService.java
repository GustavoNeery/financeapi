package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.MonthlyExpensesDto;

import java.util.UUID;

public interface MonthlyExpenseService {
    public UUID save(MonthlyExpensesDto monthlyExpensesDto);
}
