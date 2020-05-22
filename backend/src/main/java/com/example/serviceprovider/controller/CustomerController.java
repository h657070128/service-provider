package com.example.serviceprovider.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviceprovider.model.Customer;
import com.example.serviceprovider.service.CustomerService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }
}