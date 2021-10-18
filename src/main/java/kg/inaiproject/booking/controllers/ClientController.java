package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.repos.WalletRepo;
import kg.inaiproject.booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private WalletRepo walletRepo;

    private User currentUser;

    //MAPPINGS FOR REGISTRATION
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

    //MAPPINGS FOR WALLET
    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        getCurrentUser();
        model.addAttribute("user", currentUser);
        model.addAttribute("wallet", walletRepo.getByUserId(currentUser.getId()));
        return "client/profile";
    }

    @PostMapping("/updateBalance")
    public String updateBalanceOfUser(@ModelAttribute("balance") Double balance) {
        getCurrentUser();
        userService.updateUserWallet(currentUser.getId(), balance);
        return "redirect:/client/profile";
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUser = userService.getUserByUsername(userDetails.getUsername());
    }
}
