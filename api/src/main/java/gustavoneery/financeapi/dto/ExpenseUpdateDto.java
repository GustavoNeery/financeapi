package gustavoneery.financeapi.dto;

import java.time.LocalDate;

public record ExpenseUpdateDto(
        String name,
        Double purchaseValue,
        LocalDate transactionDate,
        String category) {
}
