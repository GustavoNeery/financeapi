package gustavoneery.financeapi.dto;

import gustavoneery.financeapi.model.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResponseDto(UUID id,
                                 String name,
                                 Double purchaseValue,
                                 LocalDate transactionDate,
                                 int installmentsCount,
                                 String category,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
    public ExpenseResponseDto(Expense expense) {
        this(expense.getId(),
                expense.getName(),
                expense.getPurchaseValue(),
                expense.getTransactionDate(),
                expense.getInstallmentsCount(),
                expense.getCategory(),
                expense.getCreatedAt(),
                expense.getUpdatedAt());
    }
}
