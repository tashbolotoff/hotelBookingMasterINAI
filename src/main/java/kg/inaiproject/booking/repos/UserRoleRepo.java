package kg.inaiproject.booking.repos;

import kg.inaiproject.booking.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepo extends JpaRepository<UserRole, Long> {
    UserRole getByName(String name);
}
