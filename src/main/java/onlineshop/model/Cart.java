package onlineshop.model;

import java.util.List;
import javax.management.ConstructorParameters;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Cart {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @OneToMany
  private List<Item> items;

  @OneToOne
  private Person person;

  public Cart(List<Item> items, Person person) {
    this.items = items;
    this.person = person;
  }

  public Cart() {
  }
}
