package sk.umb.example.library.book.pesistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sk.umb.example.library.book.pesistence.entity.BookEntity;
@Repository
public interface BookRepository extends CrudRepository<BookEntity, Long> {
}
