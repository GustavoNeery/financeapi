package gustavoneery.financeapi.controller;

import gustavoneery.financeapi.dto.ResponseError;
import gustavoneery.financeapi.model.MonthCost;
import gustavoneery.financeapi.service.interfaces.MonthCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/monthCosts")
public class MonthCostController {

    private MonthCostService monthCostService;

    @Autowired
    public MonthCostController(MonthCostService monthCostService) {
        this.monthCostService = monthCostService;
    }

    @GetMapping
    public ResponseEntity<List<MonthCost>> findAll() {
        List<MonthCost> monthCosts = monthCostService.findAll();

        return new ResponseEntity<>(monthCosts, HttpStatus.OK);
    }

    @GetMapping("/{period}")
    public ResponseEntity<Object> findByPeriod(@PathVariable("period") LocalDate period) {
        try {
            MonthCost monthCost = monthCostService.findByPeriod(period).orElseThrow();
            return new ResponseEntity<>(monthCost, HttpStatus.OK);
        } catch (Exception e) {
            var errorDto = ResponseError.notFound(e.getMessage());
            return ResponseEntity.status(errorDto.status()).body(errorDto);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id) {
        monthCostService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
