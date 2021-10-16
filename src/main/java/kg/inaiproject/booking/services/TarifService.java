package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.Tarif;

import java.util.List;

public interface TarifService {

    Tarif create(Tarif tarif);

    List<Tarif> findAll();

    Tarif update(Tarif tarif);

    Tarif getById(Long id);
}
