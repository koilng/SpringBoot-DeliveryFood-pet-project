package onlineshop.service;

import java.util.List;
import onlineshop.model.Category;
import onlineshop.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService<Category>{

  private final CategoryRepository categoryRepository;

  @Autowired
  public CategoryServiceImpl(CategoryRepository categoryRepository) {
    this.categoryRepository = categoryRepository;
  }


  @Override
  public Category findById(Long id) {
    return categoryRepository.findById(id).get();
  }

  @Override
  public List<Category> index() {
    return categoryRepository.findAll();
  }

  @Override
  public void save(Category element) {
    categoryRepository.save(element);
  }

  @Override
  public void deleteById(Long id) {
    categoryRepository.deleteById(id);
  }
}
