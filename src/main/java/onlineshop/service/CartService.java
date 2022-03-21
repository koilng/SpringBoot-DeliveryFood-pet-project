package onlineshop.service;

import java.util.List;
import onlineshop.model.Item;

public interface CartService {

  void addToCart(Item item);
  void deleteFromCart(Item item);
}
