package demo.microservices.customers.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.microservices.customers.models.CustomerDto;
import demo.microservices.customers.services.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
	// 1. Hacer un Get para obtener un cliente
	// 2. Hacer un Post para grabar un nuevo cliente
	// 3. Hacer un Put para actualizar un cliente existente
	// 4. Hacer un Delete para eliminar un cliente existente
	private CustomerService customerService;
	
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {
		return new ResponseEntity<CustomerDto>(customerService.getCustomerById(customerId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity handlePost(@RequestBody CustomerDto customerDto, HttpServletRequest request) {
		
		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);
				
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
				.replacePath(null)
				.build()
				.toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", baseUrl + "/api/v1/customer/" + savedDto.getId().toString());
		return new ResponseEntity(savedDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity handlePut(@PathVariable("customerId") UUID customerId, CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteById(customerId);

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	

}
