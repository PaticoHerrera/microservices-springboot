package demo.microservices.customers.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.microservices.customers.models.CustomerDto;
import demo.microservices.customers.services.CustomerService;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;	
	
	@MockBean
	CustomerService customerService;
	
	@MockBean
	ServiceComm serviceComm;
	
	@Test
	void testGetCustomer() throws Exception {
		given(customerService.getCustomerById(any())).willReturn(getValidCustomerDto());
		
		mockMvc.perform(get("/api/v1/customer" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON)
				
				);
	}
	
	@Test
	void testHandlePost() throws Exception{
		CustomerDto productDto = getValidCustomerDto();
		String productDtoJson = objectMapper.writeValueAsString(productDto);
		
		given(customerService.saveNewCustomer(any())).willReturn(getValidCustomerDto());
		
		mockMvc.perform(post("/api/v1/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productDtoJson))
				.andExpect(status().isCreated());
		
	}	
	
	@Test
	void testHandlePut() throws Exception{
		CustomerDto customerDto = getValidCustomerDto();
		String customerDtoJson = objectMapper.writeValueAsString(customerDto);
		
		given(customerService.updateCustomer(any(), any())).willReturn(getValidCustomerDto());
		
		mockMvc.perform(put("/api/v1/customer/"+customerDto.getId().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(customerDtoJson))
				.andExpect(status().isNoContent());		
	}
	
	CustomerDto getValidCustomerDto() {
		return CustomerDto.builder()
				.id(UUID.randomUUID())
				.name("Juan Carlos")
				.lastname("Gomez")
				.address("Calle de los Arcos, AG")
				.build();
	}	
}
