package gustavoneery.financeapi.controller;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/expenses")
public class ExpenseController {

    final private ExpenseService expenseService;

    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<UUID> save(@RequestBody ExpenseDto dto){
        UUID id = expenseService.save(dto);

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }
}
