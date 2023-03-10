package sk.umb.example.library.customer.controller;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sk.umb.example.library.customer.service.CustomerRequestDTO;
import sk.umb.example.library.customer.service.CustomerDetailDTO;
import sk.umb.example.library.customer.service.CustomerService;

import java.util.Collections;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/api/customers")
    public List<CustomerDetailDTO> searchCustomer(@RequestParam(required = false) String lastName) {
        System.out.println("Search customer called.");

        return customerService.getAllCustomers();
    }

    @GetMapping("/api/customers/{customerId}")
    public CustomerDetailDTO getCustomer(@PathVariable Long customerId) {
        System.out.println("Get customer called.");
        return customerService.getCustomerById(customerId);
    }

    @PostMapping("/api/customers")
    public Long createCustomer(@RequestBody CustomerRequestDTO customerRequestDTO) {
        System.out.println("Create customer called:");
        return customerService.createCustomer(customerRequestDTO);
    }

    @PutMapping("/api/customers/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @RequestBody CustomerRequestDTO customerRequestDTO) {
        System.out.println("Update customer called: ID = " + customerId);
        customerService.updateCustomer(customerId, customerRequestDTO);
    }

    @DeleteMapping("/api/customers/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        System.out.println("Delete customer called: ID = " + customerId);
        customerService.deleteCustomer(customerId);
    }
}
