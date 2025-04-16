package gustavoneery.financeapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResponseWithIdDto(UUID id,
                                       String name,
                                       Double purchaseValue,
                                       LocalDate transactionDate,
                                       int installmentsCount,
                                       String category,
                                       LocalDateTime createdAt,
                                       LocalDateTime updatedAt) {
}
