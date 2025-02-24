package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.exceptions.MonthCostNotFoundException;
import gustavoneery.financeapi.model.Expense;
import gustavoneery.financeapi.model.MonthCost;
import gustavoneery.financeapi.model.enums.Operation;
import gustavoneery.financeapi.repository.MonthCostRepository;
import gustavoneery.financeapi.service.interfaces.MonthCostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MonthCostServiceImpl implements MonthCostService {

    private MonthCostRepository monthCostRepository;

    @Autowired
    public MonthCostServiceImpl(MonthCostRepository monthCostRepository) {
        this.monthCostRepository = monthCostRepository;
    }

    public void save(MonthCostDto monthCostDto){
        MonthCost monthCost = new MonthCost();
        monthCost.setPeriod(monthCostDto.period());
        monthCost.setTotalSpent(monthCostDto.totalSpent());

        monthCostRepository.save(monthCost);
    }

    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue, Operation operation){
        if(operation.equals(Operation.ADD)){
            monthCost.setTotalSpent(monthCost.getTotalSpent() + purchaseValue);
        } else {
            monthCost.setTotalSpent(monthCost.getTotalSpent() - purchaseValue);
        }
        monthCostRepository.save(monthCost);
    }

    public void findMonthCostByExpense(Expense expense, Operation operation) {
        LocalDate periodWithDayOne = expense.getTransactionDate().withDayOfMonth(1);
        Optional<MonthCost> monthCost = monthCostRepository.findByPeriod(periodWithDayOne);
        if(monthCost.isEmpty()) {
            this.save(new MonthCostDto(periodWithDayOne, expense.getPurchaseValue()));
        } else {
            this.updateTotalSpent(monthCost.get(), expense.getPurchaseValue(), operation);
        }
    }

    public List<MonthCost> findAll() {
        return monthCostRepository.findAll();
    }

    public MonthCost findByPeriod(LocalDate period) {
        LocalDate periodWithDayOne = period.withDayOfMonth(1);
        Optional<MonthCost> monthCost = monthCostRepository.findByPeriod(periodWithDayOne);
        if(monthCost.isPresent()){
            return monthCost.get();
        }
        throw new MonthCostNotFoundException("Month cost with this period: "+periodWithDayOne+" not found");
    }

    public void delete(UUID id) {
        MonthCost monthCost = monthCostRepository.findById(id).orElseThrow();
        monthCostRepository.delete(monthCost);
    }
}
