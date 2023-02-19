package sk.umb.example.library.book.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
public class BookController {
    @GetMapping("/api/books/list")
    public List listCategories(@RequestParam(required = false) String Name){
        System.out.println("List book called");

        return Collections.emptyList();
    }
    @GetMapping("/api/books/retrieve")
    public List retrieveDetail(@RequestParam(required = false) String Name){
        System.out.println("Details of book called");

        return Collections.emptyList();
    }
    @PostMapping("/api/books")
    public void createCategory(){
        System.out.println("Create book called");
    }
    @PutMapping("/api/books/{booksId}")
    public void updateCategory(@PathVariable Long booksId){
        System.out.println("Update book called ID: "+ booksId);
    }
    @DeleteMapping("/api/books/{booksId}")
    public void deleteCategory(@PathVariable Long booksId){
        System.out.println("Delete book called ID: "+ booksId);
    }
}
