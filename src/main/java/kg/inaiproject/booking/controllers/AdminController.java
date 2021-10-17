package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.services.UserRoleService;
import kg.inaiproject.booking.services.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    //MAPPINGS FOR USERS
    @GetMapping("/user/list")
    public String getUserList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/users_list";
    }

    @GetMapping("/user/{id}")
    public String getEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        model.addAttribute("roles", userRoleService.findAll());
        model.addAttribute("sexs", Sex.values());
        return "users/users_form";
    }

    @PostMapping("/user/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/user/add")
    public String getAddForm(Model model) {
        model.addAttribute("roles", userRoleService.findAll());
        model.addAttribute("sexs", Sex.values());
        return "users/users_form";
    }

    @PostMapping("/user/add")
    public String addUser(@ModelAttribute("user") User user) throws ParseException {
        userService.create(user);
        return "redirect:/admin/user/list";
    }
}
