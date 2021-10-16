package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepo extends JpaRepository<Period, Long> {
}
