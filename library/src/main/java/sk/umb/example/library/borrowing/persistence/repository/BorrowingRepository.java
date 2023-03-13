package sk.umb.example.library.borrowing.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.borrowing.persistence.entity.BorrowingEntity;

import java.util.List;

@Repository
public interface BorrowingRepository extends CrudRepository<BorrowingEntity, Long> {
    @Override
    List<BorrowingEntity> findAll();
}
