package kg.inaiproject.booking.services;

import kg.inaiproject.booking.entities.User;

import java.text.ParseException;
import java.util.List;

public interface UserService {

    User create(User user) throws ParseException;

    User createAccount(User user) throws ParseException;

    User update(User user);

    List<User> findAll();

    User getById(Long id);

    User getUserByUsername(String username);

    void updateUserWallet(Long id, Double cash);

}
