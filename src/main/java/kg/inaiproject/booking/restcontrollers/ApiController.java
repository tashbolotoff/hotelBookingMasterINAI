package kg.inaiproject.booking.restcontrollers;

import kg.inaiproject.booking.entities.Apartment;
import kg.inaiproject.booking.entities.BonusCard;
import kg.inaiproject.booking.entities.Tarif;
import kg.inaiproject.booking.services.ApartmentService;
import kg.inaiproject.booking.services.BonusCardService;
import kg.inaiproject.booking.services.TarifService;
import kg.inaiproject.booking.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ApiController {

    @Autowired
    private ApartmentService apartmentService;

    @Autowired
    private TarifService tarifService;

    @Autowired
    private BonusCardService bonusCardService;

    @Autowired
    private WalletService walletService;

    @GetMapping("/getApartmentsByTarifId/{id}")
    public List<Apartment> getApartments(@PathVariable("id") Long id) {
        return apartmentService.findAllForClient(id, true);
    }

    @GetMapping("/getTarifById/{id}")
    public Tarif getTarif(@PathVariable("id") Long id) {
        return tarifService.getById(id);
    }

    @GetMapping("/getBonusCardByUserId/{id}")
    public Double getByUserId(@PathVariable("id") Long id) {
        return bonusCardService.getByUserId(id) != null ? bonusCardService.getByUserId(id).getBalance() : 0;
    }
    @GetMapping("/getWalletBalanceByUserId/{id}")
    public Double getByWalletBalanceUserId(@PathVariable("id") Long id) {
        return walletService.getByUserId(id) != null ? walletService.getByUserId(id).getBalance() : 0;
    }

}
