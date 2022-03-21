package onlineshop.service;

import onlineshop.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class CartServiceImpl implements CartService {
    private final List<Item> shoppingCart;
    private final ItemServiceImpl itemServiceImpl;

    @Autowired
    public CartServiceImpl(List<Item> shoppingCart, ItemServiceImpl itemServiceImpl) {
        this.shoppingCart = shoppingCart;
        this.itemServiceImpl = itemServiceImpl;
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
                    itemServiceImpl.save(item);
                }
            });
        } else {
            item.setQuantity(item.getQuantity()-1);
            itemServiceImpl.save(item);

            item.setQuantity(1);
            shoppingCart.add(item);
        }
    }

    @Override
    public void deleteFromCart(Item item) {

    }
}
