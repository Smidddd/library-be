package sk.umb.example.library.book.pesistence.entity;

import javax.persistence.*;
import java.util.ArrayList;


@Entity
public class BookEntity {
    @GeneratedValue
    @Id
    private Long bookId;
    private String authorFirstName;
    private String authorLastName;

    private String title;
    private String isbn;
    private Long count;
    private ArrayList<Long> categoryIds;
    @ManyToMany
    @JoinTable(name="category_book",
            joinColumns=@JoinColumn(name="book_id"),
            inverseJoinColumns=@JoinColumn(name="category_ids"))

    public ArrayList<Long> getCategoryIds() {
        return categoryIds;
    }

    public void setCategoryIds(ArrayList<Long> categoryIds) {
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
