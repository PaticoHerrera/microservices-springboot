package demo.microservices.customers.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import demo.microservices.customers.models.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID customerId) {
		// vida real: Llamamos a un servicio de un repositorio, para recuperar el valor.
		// mock
		return CustomerDto.builder()
				.id(customerId)
				.name("Juan")
				.lastname("Vega")
				.address("Ciudad de Guatemala")
				.build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {
		return CustomerDto.builder()
				.id(customerDto.getId())
				.name(customerDto.getName())
				.lastname(customerDto.getLastname())
				.address(customerDto.getAddress())
				.build();
	}

	@Override
	public CustomerDto updateCustomer(UUID customerId, CustomerDto customerDto) {
		log.info("Actualizando customer" + customerDto.getName());
		return CustomerDto.builder()
				.id(customerDto.getId())
				.name(customerDto.getName())
				.lastname(customerDto.getLastname())
				.address(customerDto.getAddress())
				.build();

	}

	@Override
	public void deleteById(UUID customerId) {
		log.info("Eliminando customer " + customerId.toString());

	}

}
