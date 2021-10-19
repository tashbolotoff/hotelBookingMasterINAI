package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.BonusCard;
import kg.inaiproject.booking.entities.Booking;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.entities.Wallet;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        model.addAttribute("periods", periodService.findAll());
        model.addAttribute("currentUser", currentUser);
        return "booking/booking_form";
    }

    @PostMapping("/add")
    public String addBooking(@ModelAttribute("booking") Booking booking, @ModelAttribute("sum") Double sum,
                             @ModelAttribute("sumBonus") Double sumBonus, @ModelAttribute("generalSum") Double generalSum,
                             @ModelAttribute("startDate") String startDate, @ModelAttribute("endDate") String endDate) throws ParseException {

        Wallet wallet = walletService.getByUserId(currentUser.getId());
        BonusCard bonusCard = bonusCardService.getByUserId(currentUser.getId());

        if (wallet.getBalance() >= generalSum) {
            wallet.setBalance(wallet.getBalance() - generalSum);
            if (bonusCard != null && bonusCard.getBalance() >= 500) {
                double bonusForOneNight = Math.floor(bonusCard.getBalance() / 500) * 5;
//                SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//                String inputString1 = startDate;
//                String inputString2 = endDate;
//                long days = 0;
//                try {
//                    Date date1 = myFormat.parse(inputString1);
//                    Date date2 = myFormat.parse(inputString2);
//                    days = date2.getTime() - date1.getTime();
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//                double bonusForAllDays = bonusForOneNight * TimeUnit.DAYS.convert(days, TimeUnit.MILLISECONDS);
                double minusFromBonusCard = 500 * Math.floor(bonusCard.getBalance() / 500);

                double newBonuses = generalSum / 10;
                bonusCard.setBalance((bonusCard.getBalance() - minusFromBonusCard) + newBonuses);
            }

            bookingService.create(booking, currentUser);
            return "redirect:/booking/list";
        }


        return "redirect:/booking/list";
    }

    private void getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        currentUser = userService.getUserByUsername(userDetails.getUsername());
    }
}
