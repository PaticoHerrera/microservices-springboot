package demo.microservices.cliente.clients;

import java.net.URI;
import java.util.UUID;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import demo.microservices.cliente.models.CustomerDto;

@Component
@Configuration
@ConfigurationProperties(value = "demo.customer", ignoreUnknownFields=false)
public class CustomerClient {
	
	public final String CUSTOMER_PATH_V1 = "/api/v1/customer/";
	private String host;
	
	// Forma sencilla de consumir un webservice, mediante una plantilla REST.
	private final RestTemplate restTemplate;
	
	public CustomerClient(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}
	
	public CustomerDto getCustomerById(UUID uuid) {
		return restTemplate.getForObject(host + CUSTOMER_PATH_V1 + uuid.toString(), CustomerDto.class);
	}
	
	public URI saveNewCustomerDto(CustomerDto customerDto) {
		return restTemplate.postForLocation(host + CUSTOMER_PATH_V1, customerDto);
	}

	public void updateCustomerDto(UUID uuid, CustomerDto customerDto) {
		restTemplate.put(host + CUSTOMER_PATH_V1  + UUID.randomUUID().toString() , customerDto);
	}

	public void deleteCustomerDto(UUID uuid) {
		restTemplate.delete(host + CUSTOMER_PATH_V1  + UUID.randomUUID().toString());
	}
	
	public void setHost(String host) {
		this.host = host;
	}

}
