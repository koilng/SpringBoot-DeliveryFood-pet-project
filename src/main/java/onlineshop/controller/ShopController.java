package onlineshop.controller;

import java.util.ArrayList;
import java.util.List;
import onlineshop.model.Category;
import onlineshop.model.Item;
import onlineshop.service.CartServiceImpl;
import onlineshop.service.ItemServiceImpl;
import onlineshop.service.PersonServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping()
public class ShopController {

    private final PersonServiceImpl personServiceImpl;
    private final ItemServiceImpl itemServiceImpl;
    private final CartServiceImpl cartServiceImpl;
    private final SessionFactory sessionFactory;

    @Autowired
    public ShopController(PersonServiceImpl personServiceImpl, ItemServiceImpl itemServiceImpl, CartServiceImpl cartServiceImpl, SessionFactory sessionFactory) {
        this.personServiceImpl = personServiceImpl;
        this.itemServiceImpl = itemServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
        this.sessionFactory = sessionFactory;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("items", this.itemServiceImpl.index());
        return "mainpage";
    }

    @GetMapping("/items/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String addPage(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("category", new Category(1L, "Bread"));
        model.addAttribute("categories", new ArrayList<>(List.of(new Category(1L, "Bread"))));
        //TODO: delete modelAttribute
        return "add";
    }

    @PostMapping("/items/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String add(@ModelAttribute("item") Item item, @ModelAttribute("category") Category category) {
        System.out.println(item);
        System.out.println(item.getCategory());
        System.out.println(category);
        Session session = sessionFactory.openSession();

        Category category1 = session.get(Category.class, 1L);

        item.setCategory(category);
        this.itemServiceImpl.save(item);
        return "add";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", this.cartServiceImpl.index());
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@ModelAttribute("item") Item item) {
        this.cartServiceImpl.addToCart(item);
        return "redirect:/";
    }
}
