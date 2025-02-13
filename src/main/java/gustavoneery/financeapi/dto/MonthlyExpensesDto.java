package gustavoneery.financeapi.dto;

import java.time.LocalDate;

public record MonthlyExpensesDto(LocalDate monthYear, Double totalSpent) {
}
