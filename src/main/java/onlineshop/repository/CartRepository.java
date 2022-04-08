package onlineshop.repository;

import java.util.Optional;
import onlineshop.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
  Optional<Cart> findByPersonId(Long id);
  Optional<Cart> deleteByPersonId(Long id);
//  void saveItemByCartId(Long id);
}
