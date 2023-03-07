package sk.umb.example.library.category.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

}
