package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Period;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.PeriodRepo;
import kg.inaiproject.booking.services.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PeriodServiceImpl implements PeriodService {

    @Autowired
    private PeriodRepo periodRepo;

    @Override
    public Period create(Period period) throws ParseException {
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strStartDate = dateFormat.format(startDate);
        String strEndDate = dateFormat.format(endDate);
        period.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strStartDate));
        period.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strEndDate));
        return periodRepo.save(period);
    }

    @Override
    public List<Period> findAll() {
        return periodRepo.findAll();
    }

    @Override
    public Period update(Period period) {
        Date startDate = period.getStartDate();
        Date endDate = period.getEndDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String strStartDate = dateFormat.format(startDate);
        String strEndDate = dateFormat.format(endDate);
        return periodRepo.findById(period.getId())
                .map(newPeriod -> {
                    try {
                        newPeriod.setStartDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strStartDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    try {
                        newPeriod.setEndDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strEndDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newPeriod.setPeriodType(period.getPeriodType());
                    return periodRepo.save(period);
                }).orElseThrow(() -> new RecordNotFoundException("Period not found with id "+period.getId()));
    }

    @Override
    public Period getById(Long id) {
        return periodRepo.getById(id);
    }
}
