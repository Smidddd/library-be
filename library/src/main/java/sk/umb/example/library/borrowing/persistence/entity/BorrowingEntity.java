package sk.umb.example.library.borrowing.persistence.entity;

import sk.umb.example.library.book.pesistence.entity.BookEntity;
import sk.umb.example.library.customer.persistence.entity.CustomerEntity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
@Entity
public class BorrowingEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Date date = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    @ManyToOne
    private CustomerEntity customer;
    
    @ManyToOne
    private BookEntity bookEntity;

}
