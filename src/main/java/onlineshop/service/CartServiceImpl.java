package onlineshop.service;

import java.util.ArrayList;
import onlineshop.model.Cart;
import onlineshop.model.Item;
import onlineshop.model.Person;
import onlineshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class CartServiceImpl implements CartService<Cart> {
    private final Cart cart = new Cart();
    private final CartRepository cartRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public List<Cart> index() {
        return null;
    }

    @Override
    public void save(Cart element) {
        element.setItems(new ArrayList<>());
        element.setPerson(new ArrayList<>());
        element.getItems().add(new Item());
        element.getPerson().add(new Person());
        cartRepository.save(element);
    }

    @Override
    public void deleteById(Long id) {

    }


    /*private Cart cart;
    private final List<Item> shoppingCart;
    private final ItemServiceImpl itemServiceImpl;

    @Autowired
    public CartServiceImpl(List<Item> shoppingCart, ItemServiceImpl itemServiceImpl) {
        this.shoppingCart = shoppingCart;
        this.itemServiceImpl = itemServiceImpl;
        cart.getItem().
    }

    public List<Item> index() {
        return shoppingCart;
    }

    @Override
    public void addToCart(Item item) {
        if (shoppingCart.stream().anyMatch(i -> i.getName().equals(item.getName()))) {
            shoppingCart.forEach(cartItem -> {
                if (cartItem.getName().equals(item.getName()) & item.getQuantity() > 0) {
                    cartItem.setQuantity(cartItem.getQuantity()+1);
                    item.setQuantity(item.getQuantity()-1);
                    shoppingCart.add(item);
                }
            });
        } else {
            item.setQuantity(item.getQuantity()-1);
            shoppingCart.add(item);
        }
    }

    @Override
    public void deleteFromCart(Item item) {

    }*/
}
