package com.example.serviceprovider.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.serviceprovider.model.Customer;
import com.example.serviceprovider.model.Service;
//import com.example.serviceprovider.model.ServiceSubscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	Optional<Customer> findById(Long id);
}
