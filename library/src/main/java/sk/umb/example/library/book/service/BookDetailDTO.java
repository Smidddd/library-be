package sk.umb.example.library.book.service;

import sk.umb.example.library.category.persistence.entity.CategoryEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BookDetailDTO {
    private Long bookId;
    private String authorFirstName;
    private String authorLastName;

    private String title;
    private String isbn;
    private Long count;

    private Set<CategoryEntity> categoryIds;


    public Set<CategoryEntity> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Set<CategoryEntity> categoryIds) {
        this.categoryIds = categoryIds;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
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

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
