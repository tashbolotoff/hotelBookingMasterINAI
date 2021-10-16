package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Period;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.PeriodRepo;
import kg.inaiproject.booking.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepo periodRepo;

    @Override
    public Period create(Period period) {
        return periodRepo.save(period);
    }

    @Override
    public List<Period> findAll() {
        return periodRepo.findAll();
    }

    @Override
    public Period update(Period period) {
        return periodRepo.findById(period.getId())
                .map(newPeriod -> {
                    newPeriod.setStartDate(period.getStartDate());
                    newPeriod.setEndDate(period.getEndDate());
                    newPeriod.setPeriodType(period.getPeriodType());
                    return periodRepo.save(period);
                }).orElseThrow(() -> new RecordNotFoundException("Period not found with id "+period.getId()));
    }

    @Override
    public Period getById(Long id) {
        return periodRepo.getById(id);
    }
}
