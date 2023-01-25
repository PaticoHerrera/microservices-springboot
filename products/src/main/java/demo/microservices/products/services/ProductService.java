package demo.microservices.products.services;

import java.util.UUID;

import demo.microservices.products.models.ProductDto;


public interface ProductService {

	ProductDto getProductById(UUID customerId);
	
	ProductDto saveNewProduct(ProductDto customerDto);
	
	ProductDto updateProduct(UUID customerId, ProductDto productDto);
	
	void deleteById(UUID customerId);	
}
