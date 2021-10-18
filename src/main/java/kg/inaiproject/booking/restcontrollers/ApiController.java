package kg.inaiproject.booking.restcontrollers;

import kg.inaiproject.booking.entities.Apartment;
import kg.inaiproject.booking.entities.BonusCard;
import kg.inaiproject.booking.entities.Tarif;
import kg.inaiproject.booking.services.ApartmentService;
import kg.inaiproject.booking.services.BonusCardService;
import kg.inaiproject.booking.services.TarifService;
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

    @GetMapping("/getApartmentsByTarifId/{id}")
    public List<Apartment> getApartments(@PathVariable("id") Long id) {
        return apartmentService.findAllForClient(id, true);
    }

    @GetMapping("/getTarifById/{id}")
    public Tarif getTarif(@PathVariable("id") Long id) {
        return tarifService.getById(id);
    }

    @GetMapping("/getBonusCardByUserId/{id}")
    public BonusCard getByUserId(@PathVariable("id") Long id) {
        return bonusCardService.getByUserId(id);
    }

}
