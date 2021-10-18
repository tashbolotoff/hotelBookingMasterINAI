package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Booking;
import kg.inaiproject.booking.entities.User;

import java.text.ParseException;
import java.util.List;

public interface BookingService {

    Booking create(Booking booking, User user) throws ParseException;

    List<Booking> findAll();

    Booking getById(Long id);

    Booking update(Booking booking);

    List<Booking> getAllByUserId(Long id);
}
