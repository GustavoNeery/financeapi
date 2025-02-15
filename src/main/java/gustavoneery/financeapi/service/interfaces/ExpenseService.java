package gustavoneery.financeapi.service.interfaces;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
    public UUID save(ExpenseDto expenseDto);

    public List<ExpenseResponseWithIdDto> findAllWithId();

    public List<ExpenseResponseDto> findAll();

}
