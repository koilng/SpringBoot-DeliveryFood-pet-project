package onlineshop.service;

import onlineshop.models.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    private List<Item> shoppingCart;

    @Autowired
    public CartService(List<Item> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

//    public void initialize() {
//        shoppingCart = new ArrayList<>();
//    }

    public List<Item> index() {
        return shoppingCart;
    }

    public void addToCart(Item item) {
        shoppingCart.add(item);
    }
}
