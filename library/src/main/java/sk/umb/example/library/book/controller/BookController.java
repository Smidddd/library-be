package sk.umb.example.library.book.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import sk.umb.example.library.book.service.BookDetailDTO;
import sk.umb.example.library.book.service.BookRequestDTO;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerRequestDTO;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/api/books/list")
    public List <BookDetailDTO> listBooks(@RequestParam(required = false) Long bookId){
        System.out.println("List book called");
        return bookId == null? bookService.getAllBooks()
                                        : bookService.searchBookById(bookId);
    }

    @GetMapping("/api/books/{bookId}")
    public BookDetailDTO retrieveBook(@PathVariable Long bookId){
        System.out.println("Details of book called");

        return bookService.retrieveBook(bookId);
    }

    @PostMapping("/api/books")
    public Long createBook(@RequestBody BookRequestDTO bookRequestDTO){
        System.out.println("Create book called");
        return bookService.createBook(bookRequestDTO,categoryService.getAllCategories());
    }

    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Long bookId, @RequestBody BookRequestDTO bookRequestDTO){
        System.out.println("Update book called ID: "+ bookId);
        bookService.updateBook(bookId, bookRequestDTO,categoryService.getAllCategories());
    }
    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        System.out.println("Delete book called ID: "+ bookId);
        bookService.deleteBook(bookId);
    }
}
