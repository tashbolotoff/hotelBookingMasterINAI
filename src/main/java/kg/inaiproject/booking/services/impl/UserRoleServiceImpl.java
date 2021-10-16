package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.UserRole;
import kg.inaiproject.booking.repos.UserRoleRepo;
import kg.inaiproject.booking.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Override
    public UserRole getById(Long id) {
        return userRoleRepo.getById(id);
    }

    @Override
    public List<UserRole> findAll() {
        return userRoleRepo.findAll();
    }
}