package demo.microservices.cliente;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.net.URI;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import demo.microservices.cliente.clients.ProductClient;
import demo.microservices.cliente.models.ProductDto;
import demo.microservices.cliente.models.ProductStyleEnum;

@SpringBootTest
public class ProductClientTest {
	
	@Autowired
	ProductClient client;
	
	@Test
	void getProductById() {
		client.getProductById(UUID.randomUUID());
	}
	
	@Test
	void testSaveNewProduct() {
		ProductDto productDto = ProductDto.builder()
			.id(UUID.randomUUID())
			.productName("Cerveza")
			.productStyle(ProductStyleEnum.BEER)
			//.createdDate(OffsetDateTime.now())
			//.lasModifiedDate(OffsetDateTime.now())
			.price(new BigDecimal(120.00))
			.quantityOnHand(150)
			.build();
		
		URI prodUri = client.saveNewProductDto(productDto);
		assertNotNull(prodUri);
		
	}
	
	@Test
	void testUpdateProduct() {
		ProductDto productDto = ProductDto.builder()
			.id(UUID.randomUUID())			
			.productName("Cerveza")
			.productStyle(ProductStyleEnum.BEER)
			.build();
		
		client.updateProductDto(productDto.getId(), productDto);
		
	}

	@Test
	void testDeleteProduct() {		
		client.deleteProductDto(UUID.randomUUID());
		
	}
	
}
