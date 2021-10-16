package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.BonusCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BonusCardRepo extends JpaRepository<BonusCard, Long> {
}
