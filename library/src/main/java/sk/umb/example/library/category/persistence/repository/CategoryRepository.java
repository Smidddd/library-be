package sk.umb.example.library.category.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;


import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    @Override
    List<CategoryEntity> findAll();
}
