package com.example.serviceprovider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviceprovider.model.Customer;
import com.example.serviceprovider.repository.CustomerRepository;
import com.example.serviceprovider.repository.ServiceRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

}
