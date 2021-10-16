package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.PeriodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodTypeRepo extends JpaRepository<PeriodType, Long> {
}
