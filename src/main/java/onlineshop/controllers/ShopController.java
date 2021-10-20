package onlineshop.controllers;

import onlineshop.models.Item;
import onlineshop.service.CartService;
import onlineshop.service.ItemService;
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

    private final PersonService personService;
    private final ItemService itemService;
    private final CartService cartService;

    @Autowired
    public ShopController(PersonService personService, ItemService itemService, CartService cartService) {
        this.personService = personService;
        this.itemService = itemService;
        this.cartService = cartService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", itemService.findAll());
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
        itemService.saveItem(item);
        return "add";
    }

    @GetMapping("/cart")
    public String cart(@ModelAttribute("item") Item item, Model model) {
        model.addAttribute("cart", cartService.index());
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@ModelAttribute("item") Item item) {
        cartService.addToCart(item);
        return "redirect:/shop";
    }

    @GetMapping("/{id}")
    //@PreAuthorize("#name == authentication.principal.username")
    public String show(@PathVariable("id") Long id, Model model) {
        model.addAttribute("person", personService.findById(id));
        return "profile";
    }

    @PostMapping("/{id}")
    public String update(@ModelAttribute("person") Person person,
                         @PathVariable("id") Long id) {
        person.setPassword(new BCryptPasswordEncoder(12).encode(person.getPassword()));
        personService.savePerson(person);
        return "profile";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        personService.deleteById(id);
        return "redirect:/shop";
    }
}
