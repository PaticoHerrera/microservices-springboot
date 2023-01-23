package demo.microservices.registry.services;

import java.util.UUID;

import demo.microservices.registry.models.AddressDto;

public interface AddressService {
	
	public AddressDto getAddressById(UUID addressId); 
	public AddressDto saveNewAddress(AddressDto addressDto);
	public AddressDto updateAddressById(AddressDto addressDto, UUID addressId);
	public void deleteAddressById(UUID addressId);

}
