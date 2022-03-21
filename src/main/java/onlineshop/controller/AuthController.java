package onlineshop.controller;

import onlineshop.model.Person;
import onlineshop.service.PersonServiceImpl;
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
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public AuthController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
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
        personServiceImpl.save(person);
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        return "mainpage";
    }
}
