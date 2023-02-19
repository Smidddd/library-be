package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
public class BorrowingController {
    @GetMapping("/api/borrowings/list")
    public List listBorrowings(@RequestParam(required = false) String name){
        System.out.println("List borrowings called");
        return Collections.emptyList();
    }
    @GetMapping("/api/borrowings/retrieve")
    public List retrieveDetail(@RequestParam(required = false) String name){
        System.out.println("Retrieve detail of borrowing called");
        return Collections.emptyList();
    }
    @PostMapping("/api/borrowings")
    public void createBorrowing(){
        System.out.println("Create borrowing called");
    }
    @PutMapping("/api/borrowings/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId){
        System.out.println("Update borrowing called ID: "+ borrowingId);
    }
    @DeleteMapping("/api/borrowings/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId){
        System.out.println("Delete borrowing called ID: "+ borrowingId);
    }
}
