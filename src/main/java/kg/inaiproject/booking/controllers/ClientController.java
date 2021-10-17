package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@Controller
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String getPageOfRegistration(Model model) {
        model.addAttribute("sexs", Sex.values());
        return "client/registration";
    }

    @PostMapping("/registration")
    public String createAccount(@ModelAttribute("user") User user) throws ParseException {
        userService.createAccount(user);
        return "redirect:/login";
    }
}
