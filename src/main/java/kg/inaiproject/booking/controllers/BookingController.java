package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.Booking;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.repos.TarifRepo;
import kg.inaiproject.booking.services.*;
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
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    private UserService userService;

    @Autowired
    private PeriodService periodService;

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private WalletService walletService;

    @Autowired
    private BonusCardService bonusCardService;

    @Autowired
    private TarifService tarifService;

    private User currentUser;

    @GetMapping("/list")
    public String getListOfBookings(Model model) {
        getCurrentUser();
        model.addAttribute("bookings", bookingService.getAllByUserId(currentUser.getId()));
        return "booking/booking_list";
    }

    @GetMapping("/add")
    public String getBookingAddForm(Model model) {
        getCurrentUser();
        model.addAttribute("apartments", apartmentService.findAllForClient(tarifService.findAll().get(0).getId(), true));
        model.addAttribute("tarifs", tarifService.findAll());
        model.addAttribute("bonuscard", bonusCardService.getByUserId(currentUser.getId()));
        model.addAttribute("wallet", walletService.getByUserId(currentUser.getId()));
        return "booking/booking_form";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute("booking") Booking booking) throws ParseException {
        bookingService.create(booking, currentUser);
        return "redirect:/booking/list";
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUser = userService.getUserByUsername(userDetails.getUsername());
    }
}
