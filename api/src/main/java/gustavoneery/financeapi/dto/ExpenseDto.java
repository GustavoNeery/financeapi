package gustavoneery.financeapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ExpenseDto(
        @NotBlank
        String name,
        @NotNull
        Double purchaseValue,
        @NotBlank
        LocalDate transactionDate,
        int installmentsCount,
        @NotBlank
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        boolean fixedExpense
        ) {
}
