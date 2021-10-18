package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {
    List<Booking> findAllByUserId(Long id);
}
