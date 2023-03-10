package sk.umb.example.library.borrowing.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
@Repository
public interface BorrowingRepository extends CrudRepository<CategoryEntity, Long> {
}