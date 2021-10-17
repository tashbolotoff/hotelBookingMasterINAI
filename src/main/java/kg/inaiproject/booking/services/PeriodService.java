package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Period;

import java.text.ParseException;
import java.util.List;

public interface PeriodService {

    Period create(Period period) throws ParseException;

    List<Period> findAll();

    Period update(Period period);

    Period getById(Long id);
}
