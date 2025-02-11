package gustavoneery.financeapi.dto;

import java.time.LocalDate;

public record ExpenseResponseDto(String name,
                                 Double purchaseValue,
                                 LocalDate transactionDate,
                                 int installmentsCount,
                                 String category) {
}
