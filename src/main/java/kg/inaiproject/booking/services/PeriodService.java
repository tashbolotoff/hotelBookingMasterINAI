package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Period;

import java.util.List;

public interface PeriodService {

    Period create(Period period);

    List<Period> findAll();

    Period update(Period period);

    Period getById(Long id);
}
