package com.bookstoreapi.controller;
import com.bookstoreapi.entity.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    //Json
    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        customer.setId(counter.incrementAndGet());
        customers.add(customer);
        return customer;
    }

    //Form Data
    @PostMapping("/register")
    public Customer registerCustomer(@RequestParam String name,
                                     @RequestParam String email,
                                     @RequestParam String address) {
        Customer customer = new Customer(counter.incrementAndGet(), name, email, address);
        customers.add(customer);
        return customer;
    }
}
