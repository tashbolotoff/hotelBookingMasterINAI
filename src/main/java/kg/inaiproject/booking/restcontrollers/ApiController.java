package kg.inaiproject.booking.restcontrollers;

import kg.inaiproject.booking.entities.Apartment;
import kg.inaiproject.booking.services.ApartmentService;
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

    @GetMapping("/getApartmentsByTarifId/{id}")
    public List<Apartment> getApartments(@PathVariable("id") Long id) {
        return apartmentService.findAllForClient(id, true);
    }

}
