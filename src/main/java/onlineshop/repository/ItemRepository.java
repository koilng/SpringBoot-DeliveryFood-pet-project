package onlineshop.repository;

import java.util.Optional;
import onlineshop.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
  Optional<Item> deleteByName(String name);
}
