package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Booking;

import java.util.List;

public interface BookingService {

    Booking create(Booking booking);

    List<Booking> findAll();

    Booking getById(Long id);

    Booking update(Booking booking);
}
