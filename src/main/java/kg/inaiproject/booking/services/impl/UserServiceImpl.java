package kg.inaiproject.booking.services.impl;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.entities.Wallet;
import kg.inaiproject.booking.exceptions.RecordNotFoundException;
import kg.inaiproject.booking.repos.UserRepo;
import kg.inaiproject.booking.repos.UserRoleRepo;
import kg.inaiproject.booking.repos.WalletRepo;
import kg.inaiproject.booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private WalletRepo walletRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User create(User user) throws ParseException {
        Date date = user.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));

        return userRepo.save(user);
    }

    @Override
    public User createAccount(User user) throws ParseException {
        Date date = user.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
        user.setUserRole(userRoleRepo.getByName("ROLE_CLIENT"));

        return userRepo.save(user);
    }

    @Override
    public User update(User user) {
        Date date = user.getBirthDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        return userRepo.findById(user.getId())
                .map(newUser -> {
                    newUser.setFirstName(user.getFirstName());
                    newUser.setLastName(user.getLastName());
                    newUser.setPatronimic(user.getPatronimic());
                    newUser.setUserRole(user.getUserRole());
                    newUser.setPassword(passwordEncoder.encode(user.getPassword()));
                    try {
                        newUser.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(strDate));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    newUser.setSex(user.getSex());
                    newUser.setEmail(user.getEmail());
                    newUser.setPhoneNumber(user.getPhoneNumber());
                    newUser.setUserRole(user.getUserRole());
                    return userRepo.save(newUser);
                }).orElseThrow(() -> new RecordNotFoundException("Not found user with id " + user.getId()));
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