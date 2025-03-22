package gustavoneery.financeapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDto(
        String name,
        Double purchaseValue,
        LocalDate transactionDate,
        int installmentsCount,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean fixedExpense
        ) {
}
