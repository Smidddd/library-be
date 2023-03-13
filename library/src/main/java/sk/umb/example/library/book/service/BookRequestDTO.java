package sk.umb.example.library.book.service;

import org.springframework.beans.factory.annotation.Autowired;
import sk.umb.example.library.book.pesistence.repository.BookRepository;
import sk.umb.example.library.category.persistence.entity.CategoryEntity;
import sk.umb.example.library.category.persistence.repository.CategoryRepository;
import sk.umb.example.library.category.service.CategoryService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookRequestDTO {

    private CategoryService categoryService;
    private String authorFirstName;
    private String authorLastName;

    private String title;
    private String isbn;
    private Long count;
    private Long[] categoryIds;

    public Long[] getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Long categoryIds[]) {
        this.categoryIds = categoryIds;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}
