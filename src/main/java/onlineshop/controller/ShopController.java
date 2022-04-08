package onlineshop.controller;

import com.sun.xml.bind.v2.TODO;
import java.util.ArrayList;
import onlineshop.model.Cart;
import onlineshop.model.Category;
import onlineshop.model.Item;
import onlineshop.service.CartServiceImpl;
import onlineshop.service.CategoryServiceImpl;
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
    private final CategoryServiceImpl categoryServiceImpl;

    @Autowired
    public ShopController(PersonServiceImpl personServiceImpl
        , ItemServiceImpl itemServiceImpl
        , CartServiceImpl cartServiceImpl
        , CategoryServiceImpl categoryServiceImpl) {
        this.personServiceImpl = personServiceImpl;
        this.itemServiceImpl = itemServiceImpl;
        this.cartServiceImpl = cartServiceImpl;
        this.categoryServiceImpl = categoryServiceImpl;
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
        model.addAttribute("categories", categoryServiceImpl.index());
        //TODO: delete modelAttribute
        return "add";
    }

    @PostMapping("/items/add")
    @PreAuthorize("hasAuthority('developers:write')")
    public String add(@ModelAttribute("item") Item item) {

        this.itemServiceImpl.save(item);
        return "add";
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("cart", this.cartServiceImpl.getCart().getItems());
        return "cart";
    }

    @PostMapping("/cart")
    public String addToCart(@ModelAttribute("item") Item item) {
        this.cartServiceImpl.addToCart(item);
        this.cartServiceImpl.save(cartServiceImpl.getCart());
        return "redirect:/";
    }
}
