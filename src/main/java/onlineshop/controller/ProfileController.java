package onlineshop.controller;

import onlineshop.model.Person;
import onlineshop.service.PersonServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public ProfileController(PersonServiceImpl personServiceImpl) {
        this.personServiceImpl = personServiceImpl;
    }

    @GetMapping()
    public String show(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getName().equals(this.personServiceImpl.findByEmail(auth.getName()).get().getEmail()) ||
                auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("developers:write"))) {
            model.addAttribute("person", this.personServiceImpl.findByEmail(auth.getName()).get());
            return "profile";
        }
        return "redirect:/";
    }

    @PostMapping()
    public String update(@ModelAttribute("person") Person person) {
        person.setPassword(new BCryptPasswordEncoder(12).encode(person.getPassword()));
        this.personServiceImpl.save(person);
        return "profile";
    }

    @PostMapping("/delete")
    public String delete() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        this.personServiceImpl.delete(this.personServiceImpl.findByEmail(auth.getName()).get());
        return "redirect:/logout";
    }

    //ADMIN METHODS BELOW

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('developers:write')")
    public String adminShow(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", this.personServiceImpl.findById(id));
         return "profile";
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if (auth.getName().equals(personService.findById(id).getEmail()) ||
//                auth.getAuthorities().stream().anyMatch(ga -> ga.getAuthority().equals("developers:write"))) {
//            System.out.println(auth.getAuthorities());
//            model.addAttribute("person", personService.findById(id));
//            return "profile";
//        }
//        return "redirect:/shop";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        this.personServiceImpl.deleteById(id);
        return "redirect:/shop";
    }
}
