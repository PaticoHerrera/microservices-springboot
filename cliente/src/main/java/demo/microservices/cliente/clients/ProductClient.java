package demo.microservices.cliente.clients;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import demo.microservices.cliente.models.ProductDto;

@Component
@Configuration
@ConfigurationProperties(value = "demo.product", ignoreUnknownFields=false)
public class ProductClient {
	
	public final String PRODUCT_PATH_V1 = "/api/v1/product/";	
	private String host;
	private String uri;
	
	private RestTemplate restTemplate;
	
	public ProductClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public ProductDto getProductById(UUID uuid) {
		return restTemplate.getForObject(host + PRODUCT_PATH_V1 + uuid.toString(), ProductDto.class);
	}
	
	public URI saveNewProductDto(ProductDto productDto) {
		// Buscamos la propiedad "Location" que sea retornada en los headers de la respuesta.
		return restTemplate.postForLocation(host + PRODUCT_PATH_V1, productDto);
	}

	public void updateProductDto(UUID uuid, ProductDto productDto) {		
		restTemplate.put(host + PRODUCT_PATH_V1 + productDto.getId().toString() , productDto);
	}

	public void deleteProductDto(UUID uuid) {
		restTemplate.delete(host + PRODUCT_PATH_V1 + UUID.randomUUID().toString());
	}
	
	public void setHost(String host) {
		this.host = host;
	}

}
