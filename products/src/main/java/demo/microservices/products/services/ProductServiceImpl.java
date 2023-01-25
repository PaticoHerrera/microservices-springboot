package demo.microservices.products.services;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import demo.microservices.products.models.ProductDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	@Override
	public ProductDto getProductById(UUID productId) {
		// Acá incluir la lógica de BD
		
		// Mock
		return ProductDto.builder()
				.id(productId)
				.productName("Cervezas")
				.price(new BigDecimal(25))
				.quantity(100)
				.build();
	}

	@Override
	public ProductDto saveNewProduct(ProductDto productDto) {
		// Acá incluir la lógica de BD
		
		// Mock
		return ProductDto.builder()
				.id(productDto.getId())
				.productName(productDto.getProductName())
				.price(productDto.getPrice())
				.quantity(productDto.getQuantity())
				.build();
	}

	@Override
	public ProductDto updateProduct(UUID customerId, ProductDto productDto) {
		log.info("Actualizamos información de: "+ productDto.getProductName());
		return ProductDto.builder()
				.id(productDto.getId())
				.productName(productDto.getProductName())
				.price(productDto.getPrice())
				.quantity(productDto.getQuantity())
				.build();
	}

	@Override
	public void deleteById(UUID customerId) {
		log.info("Eliminamos información de: "+ customerId.toString());

	}

}
