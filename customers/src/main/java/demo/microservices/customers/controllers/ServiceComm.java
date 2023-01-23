package demo.microservices.customers.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import demo.microservices.customers.models.AddressDto;

@Component
public class ServiceComm {
	
	private static final String ADDRESSES_SERVICE_LABEL = "demo.microservices.addresses";
	private static final String ADDRESSES_SERVICE_PATH = "/api/v1/addresses";
	
	@Autowired
	LoadBalancerClient client;

	public AddressDto getAddressFromId(UUID id) {
		ServiceInstance serviceInstance = client.choose(ADDRESSES_SERVICE_LABEL);
		
		String uri = serviceInstance.getUri()+ADDRESSES_SERVICE_PATH+"/"+id.toString();
		
		RestTemplate restTemplate = new RestTemplate();
		try {
			AddressDto response = restTemplate.getForObject(uri, AddressDto.class);
			return response;
		} catch(RestClientException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
