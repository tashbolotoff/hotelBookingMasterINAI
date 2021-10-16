package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.Tarif;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifRepo extends JpaRepository<Tarif, Long> {
}
