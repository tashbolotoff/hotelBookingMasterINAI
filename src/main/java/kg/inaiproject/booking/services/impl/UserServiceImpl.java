package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.UserRepo;
import kg.inaiproject.booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        return userRepo.findById(user.getId())
                .map(newUser -> {
                    newUser.setFirstName(user.getFirstName());
                    newUser.setLastName(user.getLastName());
                    newUser.setPatronomic(user.getPatronomic());
                    newUser.setUserRole(user.getUserRole());
                    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    newUser.setBirthDate(user.getBirthDate());
                    newUser.setSex(user.getSex());
                    newUser.setUserRole(user.getUserRole());
                    return userRepo.save(newUser);
                }).orElseThrow(()-> new RecordNotFoundException("Not found user with id "+user.getId()));
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepo.getById(id);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepo.getByUsername(username);
    }
}