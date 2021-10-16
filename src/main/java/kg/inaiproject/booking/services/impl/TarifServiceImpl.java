package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.Tarif;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.TarifRepo;
import kg.inaiproject.booking.services.TarifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifServiceImpl implements TarifService {

    @Autowired
    private TarifRepo tarifRepo;

    @Override
    public Tarif create(Tarif tarif) {
        return tarifRepo.save(tarif);
    }

    @Override
    public List<Tarif> findAll() {
        return tarifRepo.findAll();
    }

    @Override
    public Tarif update(Tarif tarif) {
        return tarifRepo.findById(tarif.getId())
                .map(newTarif -> {
                    newTarif.setName(tarif.getName());
                    newTarif.setPrice(tarif.getPrice());
                    return tarifRepo.save(newTarif);
                }).orElseThrow(() -> new RecordNotFoundException("Not found tarif with id " + tarif.getId()));
    }

    @Override
    public Tarif getById(Long id) {
        return tarifRepo.getById(id);
    }
}