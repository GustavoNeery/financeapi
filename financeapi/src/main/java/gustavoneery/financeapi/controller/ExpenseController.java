package gustavoneery.financeapi.controller;

import gustavoneery.financeapi.dto.*;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody ExpenseDto dto){
        try {
            UUID id = expenseService.save(dto);
            return new ResponseEntity<>(id, HttpStatus.CREATED);
        } catch (Exception e) {
            var errorDto = ResponseError.conflict(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }

    @PostMapping("/replicate/{id}")
    public ResponseEntity<UUID> replicateExpense(@PathVariable("id") UUID id){
        UUID newId = expenseService.replicateExpense(id);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseWithIdDto>> findAll(){
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{transactionDate}")
    public ResponseEntity<List<ExpenseResponseWithIdDto>> findByTransactionDate(@PathVariable("transactionDate") LocalDate transactionDate){
        return new ResponseEntity<>(expenseService.findByTransactionDate(transactionDate), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(@PathVariable("id") UUID id, @RequestBody ExpenseUpdateDto expenseDto){
        Expense expense = expenseService.update(id, expenseDto);

        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){
        expenseService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
