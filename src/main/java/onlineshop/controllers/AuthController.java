package onlineshop.controllers;

import onlineshop.models.Person;
import onlineshop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AuthController {
    private final PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
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
        person.setPassword(new BCryptPasswordEncoder(12).encode(person.getPassword()));
        personService.savePerson(person);
        return "redirect:/shop";
    }

    @GetMapping("/logout")
    public String logout() {
        return "mainpage";
    }
}
