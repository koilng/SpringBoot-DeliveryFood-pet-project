package onlineshop.service;

import java.util.ArrayList;
import java.util.Optional;
import javax.annotation.PreDestroy;
import onlineshop.model.Cart;
import onlineshop.model.Item;
import onlineshop.model.Person;
import onlineshop.repository.CartRepository;
import onlineshop.repository.ItemRepository;
import onlineshop.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Service
@SessionScope
public class CartServiceImpl implements CartService<Cart> {
    private Cart cart;
    private final CartRepository cartRepository;
    private final ItemServiceImpl itemServiceImpl;
    private final PersonServiceImpl personServiceImpl;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, ItemServiceImpl itemServiceImpl, PersonServiceImpl personServiceImpl) {
        this.cartRepository = cartRepository;
        this.itemServiceImpl = itemServiceImpl;
        this.personServiceImpl = personServiceImpl;
        setCart();
    }

    @Override
    public Cart findById(Long id) {
        return null;
    }

    @Override
    public Optional<Cart> findByPersonId(Long id) {
        return cartRepository.findByPersonId(id);
    }

    @Override
    public List<Cart> index() {
        return null;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByPersonId(Long id) {
        cartRepository.deleteByPersonId(id);
    }

    public Cart getCart() {
        return cart;
    }

    private void setCart() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (this.findByPersonId(personServiceImpl.findByEmail(auth.getName()).get().getId()).isEmpty()) {
            this.cart = new Cart(new ArrayList<>(), personServiceImpl.findByEmail(auth.getName()).get());
        } else {
            this.cart = this.findByPersonId(personServiceImpl.findByEmail(auth.getName()).get().getId()).get();
        }
    }

    public void addToCart(Item item) {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        Cart cart = cartRepository.findByPersonId(personServiceImpl.findByEmail(auth.getName()).get().getId()).get();
//        cart.getItems().add(item);
//        cartRepository.save(cart);
//
        this.cart.getItems().add(item);
    }

    public void deleteFromCart(Item item) {

    }

    @PreDestroy
    private void cartDestroy() {
        deleteByPersonId(this.cart.getPerson().getId());
    }
}
