package gustavoneery.financeapi.controller;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.dto.ExpenseUpdateDto;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.service.interfaces.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin("*")
@RequestMapping("/expenses")
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody ExpenseDto dto){
        UUID id = expenseService.save(dto);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @PostMapping("/replicate/{id}")
    public ResponseEntity<UUID> replicateExpense(@PathVariable("id") UUID id){
        UUID newId = expenseService.replicateExpense(id);
        return new ResponseEntity<>(newId, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> findAll(){
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/withId")
    public ResponseEntity<List<ExpenseResponseWithIdDto>> findAllWithId(){
        return new ResponseEntity<>(expenseService.findAllWithId(), HttpStatus.OK);
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
