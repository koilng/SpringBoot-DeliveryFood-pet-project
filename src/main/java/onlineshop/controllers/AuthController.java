package onlineshop.controllers;

import onlineshop.dao.ShopService;
import onlineshop.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
    private final ShopService shopService;

    @Autowired
    public AuthController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "sign up";
    }

    @PostMapping("/signup")
    public String create(@ModelAttribute("person") Person person) {

        shopService.personDAO().save(person);
        return "redirect:/shop";
    }

    @GetMapping("/logout")
    public String logout() {
        return "mainpage";
    }
}
