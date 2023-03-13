package sk.umb.example.library.book.pesistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.book.pesistence.entity.BookEntity;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
    @Override
    List<BookEntity> findAll();
}
