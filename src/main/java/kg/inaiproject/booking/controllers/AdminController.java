package kg.inaiproject.booking.controllers;

import kg.inaiproject.booking.entities.Period;
import kg.inaiproject.booking.entities.PeriodType;
import kg.inaiproject.booking.entities.Tarif;
import kg.inaiproject.booking.entities.User;
import kg.inaiproject.booking.enums.Sex;
import kg.inaiproject.booking.services.*;
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

    @Autowired
    private TarifService tarifService;

    @Autowired
    private PeriodTypeService periodTypeService;

    @Autowired
    private PeriodService periodService;

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

    //MAPPINGS FOR TARIF
    @GetMapping("/tarif/list")
    public String getTarifList(Model model) {
        model.addAttribute("tarifs", tarifService.findAll());
        return "tarif/tarif_list";
    }

    @GetMapping("/tarif/{id}")
    public String getTarifEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("tarif", tarifService.getById(id));
        return "tarif/tarif_form";
    }

    @PostMapping("/tarif/update")
    public String updateTarif(@ModelAttribute("tarif") Tarif tarif) {
        tarifService.update(tarif);
        return "redirect:/admin/tarif/list";
    }

    @GetMapping("/tarif/add")
    public String getAddTarifForm(Model model) {
        return "tarif/tarif_form";
    }

    @PostMapping("/tarif/add")
    public String addTarif(@ModelAttribute("tarif") Tarif tarif) throws ParseException {
        tarifService.create(tarif);
        return "redirect:/admin/tarif/list";
    }

    //MAPPINGS FOR PERIOD TYPE
    @GetMapping("/periodType/list")
    public String getPeriodTypeList(Model model) {
        model.addAttribute("periodTypes", periodTypeService.findAll());
        return "periodType/periodType_list";
    }

    @GetMapping("/periodType/{id}")
    public String getPeriodTypeEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("periodType", periodTypeService.getById(id));
        return "periodType/periodType_form";
    }

    @PostMapping("/periodType/update")
    public String updatePeriodType(@ModelAttribute("periodType") PeriodType periodType) {
        periodTypeService.update(periodType);
        return "redirect:/admin/periodType/list";
    }

    @GetMapping("/periodType/add")
    public String getAddPeriodTypeForm(Model model) {
        return "periodType/periodType_form";
    }

    @PostMapping("/periodType/add")
    public String addPeriodType(@ModelAttribute("periodType") PeriodType periodType) throws ParseException {
        periodTypeService.create(periodType);
        return "redirect:/admin/periodType/list";
    }

    //MAPPINGS FOR PERIOD
    @GetMapping("/period/list")
    public String getPeriodList(Model model) {
        model.addAttribute("periods", periodService.findAll());
        return "period/period_list";
    }

    @GetMapping("/period/{id}")
    public String getPeriodEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("period", periodService.getById(id));
        model.addAttribute("periodTypes", periodTypeService.findAll());
        return "period/period_form";
    }

    @PostMapping("/period/update")
    public String updatePeriod(@ModelAttribute("period") Period period) {
        periodService.update(period);
        return "redirect:/admin/period/list";
    }

    @GetMapping("/period/add")
    public String getAddPeriodForm(Model model) {
        model.addAttribute("periodTypes", periodTypeService.findAll());
        return "period/period_form";
    }

    @PostMapping("/period/add")
    public String addPeriod(@ModelAttribute("period") Period period) throws ParseException {
        periodService.create(period);
        return "redirect:/admin/period/list";
    }
}
