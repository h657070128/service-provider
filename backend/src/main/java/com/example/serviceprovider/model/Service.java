package com.example.serviceprovider.model;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "service")
public class Service {
    @Id
    @GeneratedValue(generator = "service_generator")
    @SequenceGenerator(
            name = "service_generator",
            sequenceName = "service_sequence",
            initialValue = 1000
    )
	@Column(name = "id")
    private Long id;

	@Column(name = "service_name")
	private String serviceName;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "subscribedServices", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Customer> subscribedCustomers;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Set<Customer> getSubscribedCustomers() {
		return subscribedCustomers;
	}

	public void setSubscribedCustomers(Set<Customer> subscribedCustomers) {
		this.subscribedCustomers = subscribedCustomers;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Service other = (Service) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}