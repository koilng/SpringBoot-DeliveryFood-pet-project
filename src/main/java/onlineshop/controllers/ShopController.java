package onlineshop.controllers;

import onlineshop.dao.ShopService;
import onlineshop.models.Item;
import onlineshop.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import onlineshop.models.Person;

@Controller
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    private final PersonService personService;

    @Autowired
    public ShopController(ShopService shopService, PersonService personService) {
        this.shopService = shopService;
        this.personService = personService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", shopService.itemsDAO().index());
        return "mainpage";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String addPage(@ModelAttribute("item") Item item) {
        return "add";
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String add(@ModelAttribute("item") Item item) {
        shopService.itemsDAO().save(item);
        return "add";
    }

    @GetMapping("/{name}")
    //@PreAuthorize("#name == authentication.principal.username")
    public String show(@PathVariable("name") String name, Model model) {
        model.addAttribute("person", shopService.personDAO().show(name));
        return "profile";
    }

    @PatchMapping("/{name}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("name") String name) {

        shopService.personDAO().update(name, person);
        return "profile";
    }

    @DeleteMapping("/{name}")
    public String delete(@PathVariable("name") String name) {
        shopService.personDAO().delete(name);
        return "redirect:/shop";
    }
}
