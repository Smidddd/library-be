package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
public class BookController {
    @GetMapping("/api/books/list")
    public List listBooks(@RequestParam(required = false) String name){
        System.out.println("List books called");
        return Collections.emptyList();
    }
    @GetMapping("/api/books/retrieve")
    public List retrieveDetail(@RequestParam(required = false) String name){
        System.out.println("Retrieve detail of book called");
        return Collections.emptyList();
    }
    @PostMapping("/api/books")
    public void createBook(){
        System.out.println("Create book called");
    }
    @PutMapping("/api/books/{bookId}")
    public void updateBook(@PathVariable Long bookId){
        System.out.println("Update book called ID: "+ bookId);
    }
    @DeleteMapping("/api/books/{bookId}")
    public void deleteBook(@PathVariable Long bookId){
        System.out.println("Delete book called ID: "+ bookId);
    }
}
