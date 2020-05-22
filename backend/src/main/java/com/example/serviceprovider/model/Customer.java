package com.example.serviceprovider.model;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(generator = "customer_generator")
    @SequenceGenerator(
            name = "customer_generator",
            sequenceName = "customer_sequence",
            initialValue = 1000
    )
	@Column(name = "id")
    private Long id;

	@Column(name = "customer_name")
	private String customerName;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "service_subscription", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
	private Set<Service> subscribedServices;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Set<Service> getSubscribedServices() {
		return subscribedServices;
	}

	public void setSubscribedServices(Set<Service> subscribedServices) {
		this.subscribedServices = subscribedServices;
	}
	
	
}