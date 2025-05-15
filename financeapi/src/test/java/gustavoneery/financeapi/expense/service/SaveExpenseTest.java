package gustavoneery.financeapi.expense.service;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class SaveExpenseTest {

    @Autowired
    public ExpenseService expenseService;

    @DisplayName("Verify if save Expense correctly")
    @Test
    void verifyIfSaveCorrectly(){
        UUID expenseIdCreated = expenseService.save(new ExpenseDto("teste", 175.0, LocalDate.of(2025, 5, 8), 0, "food", null, null, false));
        Expense result = expenseService.findById(expenseIdCreated);

        Expense expected = makeExpense();
        Assertions.assertEquals(expected, result);
    }

    public Expense makeExpense(){
        LocalDate data = LocalDate.of(2025, 5, 8);
        return new Expense(
                "teste",
                data,
                175.0,
                0,
                "food",
                null,
                null,
                false
        );
    }
}
