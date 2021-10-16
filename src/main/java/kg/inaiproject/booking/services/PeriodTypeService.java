package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.PeriodType;

import java.util.List;

public interface PeriodTypeService {

    PeriodType create(PeriodType periodType);

    List<PeriodType> findAll();

    PeriodType getById(Long id);

    PeriodType update(PeriodType periodType);

}
