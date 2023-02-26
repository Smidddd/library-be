package sk.umb.example.library.book.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.umb.example.library.category.service.CategoryDetailDTO;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@Service
public class BookService {
    private final AtomicLong lastIndex = new AtomicLong(0);

    private final Map<Long, BookDetailDTO> bookDatabase = new HashMap();

    public  List<BookDetailDTO> getAllBooks() {
        return new ArrayList<>(bookDatabase.values());
    }

    public List<BookDetailDTO> searchBookById(Long bookId) {
        return bookDatabase.values().stream()
                .filter(dto -> bookId.equals(dto.getBookId()))
                .toList();
    }

    public BookDetailDTO retrieveBook(Long bookId) {
        validateBookExists(bookId);

        return bookDatabase.get(bookId);
    }

    private void validateBookExists(Long bookId) {
        if (! bookDatabase.containsKey(bookId)) {
            throw new IllegalArgumentException("CategoryID: " + bookId + " does not exists!");
        }

    }

    public Long createBook(BookRequestDTO bookRequestDTO,List<CategoryDetailDTO> categories) {
        BookDetailDTO bookDetailDTO = mapToBookDetailDTO(lastIndex.getAndIncrement(),
                bookRequestDTO,categories);

        bookDatabase.put(bookDetailDTO.getBookId(), bookDetailDTO);

        return bookDetailDTO.getBookId();
    }
    private static BookDetailDTO mapToBookDetailDTO(Long index, BookRequestDTO bookRequestDTO,List<CategoryDetailDTO> categories) {
        BookDetailDTO dto = new BookDetailDTO();

        dto.setBookId(index);
        dto.setAuthorFirstName(bookRequestDTO.getAuthorFirstName());
        dto.setAuthorLastName(bookRequestDTO.getAuthorLastName());
        dto.setTitle(bookRequestDTO.getTitle());
        dto.setIsbn(bookRequestDTO.getIsbn());
        dto.setCount(bookRequestDTO.getCount());
        boolean check = true;
        int counter = 0;
        for (Long cat: bookRequestDTO.getCategoryIds()){
            if (counter != categories.size()){
                for (CategoryDetailDTO category: categories){
                    if (category.getId() != cat){
                        check = false;
                        counter++;
                    }else{
                        check = true;
                        counter =0;
                        break;
                    }
                }
            }else{
                check = false;
                break;
            }

        }
        if (check == true){
            dto.setCategoryIds(bookRequestDTO.getCategoryIds());
            return dto;
        }else {
            return null;
        }

    }

    public void updateBook(Long bookId, BookRequestDTO bookRequestDTO,List<CategoryDetailDTO> categories) {
        validateBookExists(bookId);

        BookDetailDTO bookDetailDTO = bookDatabase.get(bookId);

        if (! Strings.isEmpty(bookRequestDTO.getAuthorFirstName())) {
            bookDetailDTO.setAuthorFirstName(bookRequestDTO.getAuthorFirstName());
        }
        if (! Strings.isEmpty(bookRequestDTO.getAuthorLastName())) {
            bookDetailDTO.setAuthorLastName(bookRequestDTO.getAuthorLastName());
        }
        if (! Strings.isEmpty(bookRequestDTO.getTitle())) {
            bookDetailDTO.setTitle(bookRequestDTO.getTitle());
        }
        if (! Strings.isEmpty(bookRequestDTO.getIsbn())) {
            bookDetailDTO.setIsbn(bookRequestDTO.getIsbn());
        }
        if (bookDetailDTO.getCount() != null) {
            bookDetailDTO.setCount(bookRequestDTO.getCount());
        }
        boolean check = true;
        int counter = 0;
        for (Long cat: bookRequestDTO.getCategoryIds()){
            if (counter != categories.size()){
                for (CategoryDetailDTO category: categories){
                    if (category.getId() != cat){
                        check = false;
                        counter++;
                    }else{
                        check = true;
                        counter= 0;
                        break;
                    }
                }
            }else{
                check = false;
                break;
            }

        }
        if (bookDetailDTO.getCategoryIds() != null && check) {
            bookDetailDTO.setCategoryIds(bookRequestDTO.getCategoryIds());
        }

    }

    public void deleteBook(Long bookId) {
        bookDatabase.remove(bookId);
    }
}
