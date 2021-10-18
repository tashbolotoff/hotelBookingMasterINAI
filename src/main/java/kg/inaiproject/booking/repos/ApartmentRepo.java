package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.Apartment;
import kg.inaiproject.booking.entities.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApartmentRepo extends JpaRepository<Apartment, Long> {
    List<Apartment> findAllByTarif_IdAndIsFree(Long tarifId, Boolean value);
}
