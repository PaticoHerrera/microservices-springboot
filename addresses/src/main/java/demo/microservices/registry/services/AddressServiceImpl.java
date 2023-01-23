package demo.microservices.registry.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import demo.microservices.registry.models.AddressDto;

@Service
public class AddressServiceImpl implements AddressService {

	@Override
	public AddressDto getAddressById(UUID addressId) {		
		return AddressDto.builder()
				.ciudad("San Salvador")
				.direccion("25 calle oriente")
				.id(addressId)
				.customerId(UUID.randomUUID())
				.build();
	}

	@Override
	public AddressDto saveNewAddress(AddressDto addressDto) {
		return AddressDto.builder()
				.ciudad(addressDto.getCiudad())
				.direccion(addressDto.getDireccion())
				.id(addressDto.getId())
				.customerId(addressDto.getCustomerId())
				.build();
	}

	@Override
	public AddressDto updateAddressById(AddressDto addressDto, UUID addressId) {
		return null;
	}

	@Override
	public void deleteAddressById(UUID addressId) {

	}

}
