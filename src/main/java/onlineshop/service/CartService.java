package onlineshop.service;

import java.util.List;
import java.util.Optional;
import onlineshop.model.Item;

public interface CartService<T> extends GenericServiceInterface<T> {
  Optional<T> findByPersonId(Long id);
  void deleteByPersonId(Long id);
}
