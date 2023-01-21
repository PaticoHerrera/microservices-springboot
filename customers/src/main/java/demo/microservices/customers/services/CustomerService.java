package demo.microservices.customers.services;

import java.util.UUID;

import demo.microservices.customers.models.CustomerDto;

public interface CustomerService {
	
	CustomerDto getCustomerById(UUID customerId);
	
	CustomerDto saveNewCustomer(CustomerDto customerDto);
	
	void updateCustomer(UUID customerId, CustomerDto customerDto);
	
	void deleteById(UUID customerId);

}
