package sk.umb.example.library.category.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
public class CategoryController {
    @GetMapping("/api/categories/list")
    public List listCategories(@RequestParam(required = false) String name){
        System.out.println("List category called");
        return Collections.emptyList();
    }
    @GetMapping("/api/categories/retrieve")
    public List retrieveDetail(@RequestParam(required = false) String name){
        System.out.println("Retrieve detail of category called");
        return Collections.emptyList();
    }
    @PostMapping("/api/categories")
    public void createCategory(){
        System.out.println("Create category called");
    }
    @PutMapping("/api/categories/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId){
        System.out.println("Update category called ID: "+ categoryId);
    }
    @DeleteMapping("/api/categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        System.out.println("Delete category called ID: "+ categoryId);
    }
}
