package demo.microservices.cliente;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.microservices.cliente.clients.CustomerClient;
import demo.microservices.cliente.models.CustomerDto;

@SpringBootTest
public class CustomerClientTest {
	
	@Autowired
	CustomerClient client;
	
	@Test
	void getCustomerById() {
		client.getCustomerById(UUID.randomUUID());
	}
	
	@Test
	void testSaveNewCustomer() {
		CustomerDto customerDto = CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("Juan Carlos")
				.build();
		
		URI uri = client.saveNewCustomerDto(customerDto);
		assertNotNull(uri);
		
		System.out.println(uri.toString());
	}
	
	@Test
	void testUpdateCustomer() {
		CustomerDto customerDto = CustomerDto.builder()
			.id(UUID.randomUUID())			
			.name("Juan Carlos")
			.build();
		
		client.updateCustomerDto(customerDto.getId(), customerDto);
		
	}

	@Test
	void testDeleteCustomer() {		
		client.deleteCustomerDto(UUID.randomUUID());
		
	}
	
}
