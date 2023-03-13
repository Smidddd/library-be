package sk.umb.example.library.borrowing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.book.service.BookService;
import sk.umb.example.library.borrowing.service.BorrowingDetailDTO;
import sk.umb.example.library.borrowing.service.BorrowingRequestDTO;
import sk.umb.example.library.borrowing.service.BorrowingService;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.Collections;
import java.util.List;
@RestController
public class BorrowingController {
    @Autowired
    private BorrowingService borrowingService;
    @Autowired
    private BookService bookService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/borrowings")
    public List <BorrowingDetailDTO> listBorrowings(@RequestParam(required = false) Long borrowingId){
        System.out.println("List borrowings called");
        return borrowingService.getAllBorrowings();
    }

    @GetMapping("/api/borrowings/{borrowingId}")
    public BorrowingDetailDTO retrieveBook(@PathVariable Long borrowingId){
        System.out.println("Details of borrowing called");

        return borrowingService.retrieveBorrowing(borrowingId);
    }


    @PostMapping("/api/borrowings")
    public Long createBorrowing(@RequestBody BorrowingRequestDTO borrowingRequestDTO){
        System.out.println("Create borrowing called");
        return borrowingService.createBorrowing(borrowingRequestDTO, bookService.searchBookById(borrowingRequestDTO.getBookId()), customerService.getCustomerById(borrowingRequestDTO.getCustomerId()));
    }

    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId, @RequestBody BorrowingRequestDTO borrowingRequestDTO){
        System.out.println("Update borrowing called ID: "+ borrowingId);
        borrowingService.updateBorrowing(borrowingId, borrowingRequestDTO, bookService.searchBookById(borrowingRequestDTO.getBookId()), customerService.getCustomerById(borrowingRequestDTO.getCustomerId()));
    }
    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBook(@PathVariable Long borrowingId){
        System.out.println("Delete borrowing called ID: "+ borrowingId);
        borrowingService.deleteBorrowing(borrowingId);
    }
}
