package onlineshop.service;

import java.util.Optional;

public interface PersonService<T> extends GenericServiceInterface<T>{
  Optional<T> findByName(String name);
  Optional<T> findByEmail(String email);
}
