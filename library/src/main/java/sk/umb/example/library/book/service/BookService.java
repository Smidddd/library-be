package sk.umb.example.library.book.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.example.library.book.pesistence.entity.BookEntity;
import sk.umb.example.library.book.pesistence.repository.BookRepository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.service.CategoryDetailDTO;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;
import sk.umb.example.library.customer.persistence.repository.CustomerRepository;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class BookService {

    private final BookRepository bookRepository;


    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public  List<BookDetailDTO> getAllBooks() {
        return mapToDto(bookRepository.findAll());
    }

    public BookDetailDTO searchBookById(Long bookId) {
        validateBookExists(bookId);
        return mapToDto(bookRepository.findById(bookId).get());
    }
    private void validateBookExists(Long bookId) {
        if (! bookRepository.existsById(bookId)) {
            throw new IllegalArgumentException("BookId: " + bookId + " does not exists!");
        }

    }

    public Long createBook(BookRequestDTO bookRequestDTO, List<CategoryDetailDTO> categoryEntities) {
        return bookRepository.save(mapToEntity(bookRequestDTO,categoryEntities)).getBookId();
    }

    private BookDetailDTO mapToDto(BookEntity bookEntity) {
        BookDetailDTO dto = new BookDetailDTO();

        dto.setBookId(bookEntity.getBookId());
        dto.setAuthorFirstName(bookEntity.getAuthorFirstName());
        dto.setAuthorLastName(bookEntity.getAuthorLastName());
        dto.setTitle(bookEntity.getTitle());
        dto.setCount(bookEntity.getCount());
        dto.setIsbn(bookEntity.getIsbn());
        dto.setCategoryIds(bookEntity.getCategoryIds());


        return dto;
    }
    private List<BookDetailDTO> mapToDto(List<BookEntity> bookEntities) {
        List<BookDetailDTO> dtos = new ArrayList<>();

        for (BookEntity be : bookEntities) {
            BookDetailDTO dto = new BookDetailDTO();

            dto.setBookId(be.getBookId());
            dto.setAuthorFirstName(be.getAuthorFirstName());
            dto.setAuthorLastName(be.getAuthorLastName());
            dto.setTitle(be.getTitle());
            dto.setCount(be.getCount());
            dto.setIsbn(be.getIsbn());
            dto.setCategoryIds(be.getCategoryIds());


            dtos.add(dto);
        }

        return dtos;
    }
    private BookEntity mapToEntity(BookRequestDTO dto,List<CategoryDetailDTO> categoryEntities) {
        BookEntity be = new BookEntity();

        be.setAuthorFirstName(dto.getAuthorFirstName());
        be.setAuthorLastName(dto.getAuthorLastName());
        be.setTitle(dto.getTitle());
        be.setCount(dto.getCount());
        be.setIsbn(dto.getIsbn());

        Set<CategoryEntity> set = new HashSet<>();
        for (int i=0;i<dto.getCategoryIds().length;i++){
            for (CategoryDetailDTO ce : categoryEntities){
                if (ce.getId().equals(dto.getCategoryIds()[i])){
                    set.add(categoryDtoToEntity(ce));
                }
            }
        }
        be.setCategoryIds(set);

        return be;
    }

    private CategoryEntity categoryDtoToEntity(CategoryDetailDTO categoryDetailDTO){
        CategoryEntity ce = new CategoryEntity();

        ce.setId(categoryDetailDTO.getId());
        ce.setName(categoryDetailDTO.getName());

        return ce;
    }
    public void updateBook(Long bookId, BookRequestDTO bookRequestDTO, List<CategoryDetailDTO> categoryEntities) {
        validateBookExists(bookId);

        BookEntity bookEntity = bookRepository.findById(bookId).get();

        if (! Strings.isEmpty(bookRequestDTO.getAuthorFirstName())) {
            bookEntity.setAuthorFirstName(bookRequestDTO.getAuthorFirstName());
        }
        if (! Strings.isEmpty(bookRequestDTO.getAuthorLastName())) {
            bookEntity.setAuthorLastName(bookRequestDTO.getAuthorLastName());
        }
        if (! Strings.isEmpty(bookRequestDTO.getTitle())) {
            bookEntity.setTitle(bookRequestDTO.getTitle());
        }
        if (! Strings.isEmpty(bookRequestDTO.getIsbn())) {
            bookEntity.setIsbn(bookRequestDTO.getIsbn());
        }
        if (bookEntity.getCount() != null) {
            bookEntity.setCount(bookRequestDTO.getCount());
        }
        if (bookEntity.getCategoryIds() != null) {
            Set<CategoryEntity> set = new HashSet<>();
            for (int i=0;i<bookRequestDTO.getCategoryIds().length;i++){
                for (CategoryDetailDTO ce : categoryEntities){
                    if (ce.getId().equals(bookRequestDTO.getCategoryIds()[i])){
                        set.add(categoryDtoToEntity(ce));
                    }
                }
            }
            bookEntity.setCategoryIds(set);
        }
        bookRepository.save(bookEntity);

    }

    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }
}
