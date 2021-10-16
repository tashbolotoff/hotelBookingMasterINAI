package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Apartment;

import java.util.List;

public interface ApartmentService {

    Apartment create(Apartment anketa);

    List<Apartment> findAll();

    Apartment getById(Long id);

    Apartment update(Apartment apartment);
}
