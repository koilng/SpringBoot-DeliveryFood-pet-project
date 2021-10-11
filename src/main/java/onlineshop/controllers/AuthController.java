package onlineshop.controllers;

import onlineshop.models.Person;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "shop/login";
    }

    @PostMapping("/login")
    public String loginProcess() {
        return "redirect:/shop";
    }

    @GetMapping("/signup")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "shop/sign up";
    }
}
