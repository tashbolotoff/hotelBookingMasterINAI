package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Booking;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.BookingRepo;
import kg.inaiproject.booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public Booking create(Booking booking, User user) throws ParseException {
        Date startDate = booking.getStartDate();
        Date endDate = booking.getEndDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strStartDate = dateFormat.format(startDate);
        String strEndDate = dateFormat.format(endDate);

        booking.setStartDate(dateFormat.parse(strStartDate));
        booking.setEndDate(dateFormat.parse(strEndDate));
        booking.setUser(user);

        return bookingRepo.save(booking);
    }

    @Override
    public List<Booking> findAll() {
        return bookingRepo.findAll();
    }

    @Override
    public Booking getById(Long id) {
        return bookingRepo.getById(id);
    }

    @Override
    public Booking update(Booking booking) {
        return bookingRepo.findById(booking.getId())
                .map(newBooking -> {
                    newBooking.setApartment(booking.getApartment());
                    newBooking.setStartDate(booking.getStartDate());
                    newBooking.setEndDate(booking.getEndDate());
                    newBooking.setGeneralSum(booking.getGeneralSum());
                    newBooking.setSum(booking.getSum());
                    newBooking.setSumBonus(booking.getSumBonus());
                    newBooking.setUser(booking.getUser());
                    return bookingRepo.save(booking);
                }).orElseThrow(() -> new RecordNotFoundException("Booking not found with id "+booking.getId()));
    }

    @Override
    public List<Booking> getAllByUserId(Long id) {
        return bookingRepo.findAllByUserId(id);
    }
}
