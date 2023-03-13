package sk.umb.example.library.category.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.category.service.CategoryDetailDTO;
import sk.umb.example.library.category.service.CategoryRequestDTO;
import sk.umb.example.library.category.service.CategoryService;

import java.util.Collections;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
    @GetMapping("/api/category")
    public List <CategoryDetailDTO> listCategories(@RequestParam(required = false) String name){
        System.out.println("List categories called");

        return categoryService.getAllCategories();
    }
    @GetMapping("/api/category/{categoryId}")
    public CategoryDetailDTO retrieveCategory(@PathVariable Long categoryId){
        System.out.println("Details of category called + ");

        return categoryService.getCategorybyId(categoryId);
    }
    @PostMapping("/api/category")
    public Long createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO){
        System.out.println("Create category called");
        return categoryService.createCategory(categoryRequestDTO);
    }
    @PutMapping("/api/category/{categoryId}")
    public void updateCategory(@PathVariable Long categoryId, @RequestBody CategoryRequestDTO categoryRequestDTO){
        System.out.println("Update category called ID: "+ categoryId);
        categoryService.updateCategory(categoryId, categoryRequestDTO);
    }
    @DeleteMapping("/api/category/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId){
        System.out.println("Delete category called ID: "+ categoryId);
        categoryService.deleteCategory(categoryId);
    }
}
