package com.example.serviceprovider.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.serviceprovider.model.Customer;
import com.example.serviceprovider.model.Service;
import com.example.serviceprovider.repository.CustomerRepository;
import com.example.serviceprovider.repository.ServiceRepository;

@org.springframework.stereotype.Service
public class ServiceSubscriptionService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ServiceRepository serviceRepository;

	public Set<Service> getSubscribedServices(Customer customer) {
		return customer.getSubscribedServices();
	}
	
	public Customer subscribeService(Customer customer, Service service) {
		Set<Service> subscribedServices = customer.getSubscribedServices();
		subscribedServices.add(service);
		customer.setSubscribedServices(subscribedServices);
		return customerRepository.save(customer);
	}
	
	public Customer unsubscribeService(Customer customer, Service service) {
		Set<Service> subscribedServices = customer.getSubscribedServices();
		subscribedServices.remove(service);
		customer.setSubscribedServices(subscribedServices);
		return customerRepository.save(customer);
	}

	public List<Service> getUnsubscribedServices(Customer customer) {
		List<Service> allServices = serviceRepository.findAll();
		Set<Service> subscribedServices = customer.getSubscribedServices();
		allServices.removeAll(subscribedServices);
		return allServices;
	}
}
