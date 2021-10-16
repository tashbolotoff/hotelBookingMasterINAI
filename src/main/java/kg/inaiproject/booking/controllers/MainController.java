package kg.inaiproject.booking.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/home")
public class MainController {

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }
}
