package sk.umb.example.library.customer.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {
    @GetMapping("/api/customers")
    public List searchCustomer(@RequestParam(required = false) String lastName){
        System.out.println("Search customer called");

        return Collections.emptyList();
    }
    @PostMapping("/api/customers")
    public void createCustomer(){
        System.out.println("Create customer called");
    }
    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId){
        System.out.println("Update customer called ID: "+ customerId);
    }
    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId){
        System.out.println("Delete customer called ID: "+ customerId);
    }
}
