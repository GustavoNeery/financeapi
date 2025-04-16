package gustavoneery.financeapi.dto;

import java.time.LocalDate;

public record MonthCostDto(LocalDate period, Double totalSpent) {
}
