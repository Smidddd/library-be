package sk.umb.example.library.book.service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BookRequestDTO {
    private String authorFirstName;
    private String authorLastName;

    private String title;
    private String isbn;
    private Long count;
    List<Long> categoryIds=new ArrayList<Long>();

    public List<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(Long categoryIds[]) {
       for (int i=0;i<categoryIds.length;i++){
           this.categoryIds.add(categoryIds[i]);
       }
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
