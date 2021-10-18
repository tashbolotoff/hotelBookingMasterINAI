package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Apartment;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.ApartmentRepo;
import kg.inaiproject.booking.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    @Autowired
    private ApartmentRepo apartmentRepo;

    @Override
    public Apartment create(Apartment apartment) {
        apartment.setIsFree(true);
        return apartmentRepo.save(apartment);
    }

    @Override
    public List<Apartment> findAll() {
        return apartmentRepo.findAll();
    }

    @Override
    public Apartment getById(Long id) {
        return apartmentRepo.getById(id);
    }

    @Override
    public Apartment update(Apartment apartment) {
        return apartmentRepo.findById(apartment.getId())
                .map(newApartment -> {
                    newApartment.setName(apartment.getName());
                    newApartment.setCountOfRooms(apartment.getCountOfRooms());
                    newApartment.setTarif(apartment.getTarif());
                    newApartment.setIsFree(apartment.getIsFree());
                    return apartmentRepo.save(apartment);
                }).orElseThrow(() -> new RecordNotFoundException("Apartment not found with id "+apartment.getId()));
    }

    @Override
    public List<Apartment> findAllForClient(Long tarifId, Boolean value) {
        return apartmentRepo.findAllByTarif_IdAndIsFree(tarifId, value);
    }
}
