package sk.umb.example.library.borrowing.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
@RestController
public class BorrowingController {
    @GetMapping("/api/borrowing/list")
    public List listBorrowings(@RequestParam(required = false) String Name){
        System.out.println("List borrowing called");

        return Collections.emptyList();
    }
    @GetMapping("/api/borrowing/retrieve")
    public List retrieveBorrowings(@RequestParam(required = false) String Name){
        System.out.println("Details of borrowing called");

        return Collections.emptyList();
    }
    @PostMapping("/api/borrowing")
    public void createBorrowing(){
        System.out.println("Create borrowing called");
    }
    @PutMapping("/api/borrowing/{borrowingId}")
    public void updateBorrowing(@PathVariable Long borrowingId){
        System.out.println("Update borrowing called ID: "+ borrowingId);
    }
    @DeleteMapping("/api/borrowing/{borrowingId}")
    public void deleteBorrowing(@PathVariable Long borrowingId){
        System.out.println("Delete borrowing called ID: "+ borrowingId);
    }
}
