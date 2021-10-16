package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.PeriodType;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.PeriodTypeRepo;
import kg.inaiproject.booking.services.PeriodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeriodTypeServiceImpl implements PeriodTypeService {

    @Autowired
    private PeriodTypeRepo periodTypeRepo;

    @Override
    public PeriodType create(PeriodType periodType) {
        return periodTypeRepo.save(periodType);
    }

    @Override
    public List<PeriodType> findAll() {
        return periodTypeRepo.findAll();
    }

    @Override
    public PeriodType getById(Long id) {
        return periodTypeRepo.getById(id);
    }

    @Override
    public PeriodType update(PeriodType periodType) {
        return periodTypeRepo.findById(periodType.getId())
                .map(newPeriodType -> {
                    newPeriodType.setName(periodType.getName());
                    return periodTypeRepo.save(newPeriodType);
                }).orElseThrow(() -> new RecordNotFoundException("not found period type with id " + periodType.getId()));
    }
}