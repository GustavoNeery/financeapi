package gustavoneery.financeapi.service;

import gustavoneery.financeapi.dto.MonthCostDto;
import gustavoneery.financeapi.model.MonthCost;
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

    @Override
    public void save(MonthCostDto monthCostDto){
        MonthCost monthCost = new MonthCost();
        monthCost.setPeriod(monthCostDto.period());
        monthCost.setTotalSpent(monthCostDto.totalSpent());

        monthCostRepository.save(monthCost);
    }

    @Override
    public Optional<MonthCost> findByPeriod(LocalDate period) {
        return monthCostRepository.findByPeriod(period);
    }

    @Override
    public void updateTotalSpent(MonthCost monthCost, Double purchaseValue){
        monthCost.setTotalSpent(monthCost.getTotalSpent() + purchaseValue);
        monthCostRepository.save(monthCost);
    }

    @Override
    public List<MonthCost> findAll() {
        return monthCostRepository.findAll();
    }

    @Override
    public void delete(UUID id) {
        MonthCost monthCost = monthCostRepository.findById(id).orElseThrow();
        monthCostRepository.delete(monthCost);
    }
}
