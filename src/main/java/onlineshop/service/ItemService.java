package onlineshop.service;

public interface ItemService<T> extends GenericServiceInterface<T> {
  void deleteByName(String name);
}
