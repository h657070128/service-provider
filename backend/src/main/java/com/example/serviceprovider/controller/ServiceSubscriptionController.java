package com.example.serviceprovider.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviceprovider.exception.ResourceNotFoundException;
import com.example.serviceprovider.model.Customer;
import com.example.serviceprovider.model.Service;
import com.example.serviceprovider.repository.CustomerRepository;
import com.example.serviceprovider.repository.ServiceRepository;
import com.example.serviceprovider.service.ServiceSubscriptionService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class ServiceSubscriptionController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ServiceSubscriptionService serviceSubscriptionService;

    @GetMapping("/customers/{customerId}/services")
    public Set<Service> getServicesByCustomerId(@PathVariable Long customerId) {
		Optional<Customer> customerOp = customerRepository.findById(customerId);
		if (!customerOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find customer by id " + customerId);
		}
        return serviceSubscriptionService.getSubscribedServices(customerOp.get());
    }

    @PostMapping("/customers/{customerId}/services/{serviceId}")
    public Customer subscribeService(@PathVariable Long customerId, @PathVariable Long serviceId) {
		Optional<Customer> customerOp = customerRepository.findById(customerId);
		if (!customerOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find customer by id " + customerId);
		}
		Optional<Service> serviceOp = serviceRepository.findById(serviceId);
		if (!serviceOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find service by id " + serviceId);
		}
		return serviceSubscriptionService.subscribeService(customerOp.get(), serviceOp.get());
    }

    @DeleteMapping("/customers/{customerId}/services/{serviceId}")
    public Customer deleteServiceSubscription(@PathVariable Long customerId, @PathVariable Long serviceId) {
		Optional<Customer> customerOp = customerRepository.findById(customerId);
		if (!customerOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find customer by id " + customerId);
		}
		Optional<Service> serviceOp = serviceRepository.findById(serviceId);
		if (!serviceOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find service by id " + serviceId);
		}
		return serviceSubscriptionService.unsubscribeService(customerOp.get(), serviceOp.get());
    }

    @GetMapping("/customers/{customerId}/unsubscribed-services")
    public List<Service> getUnsubscribedServicesByCustomerId(@PathVariable Long customerId) {
		Optional<Customer> customerOp = customerRepository.findById(customerId);
		if (!customerOp.isPresent()) {
			throw new ResourceNotFoundException("Cannot find customer by id " + customerId);
		}
		return serviceSubscriptionService.getUnsubscribedServices(customerOp.get());
    }
}