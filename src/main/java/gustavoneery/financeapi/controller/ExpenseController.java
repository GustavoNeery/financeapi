package gustavoneery.financeapi.controller;

import gustavoneery.financeapi.dto.ExpenseDto;
import gustavoneery.financeapi.dto.ExpenseResponseWithIdDto;
import gustavoneery.financeapi.dto.ExpenseResponseDto;
import gustavoneery.financeapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> findAll(){
        return new ResponseEntity<>(expenseService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/withId")
    public ResponseEntity<List<ExpenseResponseWithIdDto>> findAllWithId(){
        return new ResponseEntity<>(expenseService.findAllWithId(), HttpStatus.OK);
    }
}
