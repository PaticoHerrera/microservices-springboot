package demo.microservices.products.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import demo.microservices.products.models.ProductDto;
import demo.microservices.products.services.ProductService;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	ProductService productService;	
	
	@Test
	void testGetProduct() throws Exception {
		given(productService.getProductById(any())).willReturn(getValidProductDto());
		
		mockMvc.perform(get("/api/v1/product" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON));
	}

	@Test
	void testHandlePost() throws Exception{
		ProductDto productDto = getValidProductDto();
		String productDtoJson = objectMapper.writeValueAsString(productDto);
		
		given(productService.saveNewProduct(any())).willReturn(getValidProductDto());
		
		mockMvc.perform(post("/api/v1/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productDtoJson))
				.andExpect(status().isCreated());
		
	}

	@Test
	void testHandlePut() throws Exception{
		ProductDto productDto = getValidProductDto();
		String productDtoJson = objectMapper.writeValueAsString(productDto);
		
		given(productService.updateProduct(any(), any())).willReturn(getValidProductDto());
		
		mockMvc.perform(put("/api/v1/product/"+productDto.getId().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(productDtoJson))
				.andExpect(status().isNoContent());		
	}

	ProductDto getValidProductDto() {
		return ProductDto.builder()
				.id(UUID.randomUUID())
				.productName("Agua Gaseosa")
				.price(new BigDecimal(3.25))
				.build();
	}
}
