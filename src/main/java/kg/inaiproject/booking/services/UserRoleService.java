package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.UserRole;

import java.util.List;

public interface UserRoleService {

    UserRole getById(Long id);

    List<UserRole> findAll();

}
