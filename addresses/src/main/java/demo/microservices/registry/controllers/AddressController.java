package demo.microservices.registry.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.microservices.registry.models.AddressDto;
import demo.microservices.registry.services.AddressService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {
	
	private AddressService addressService;
	
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}
	
	@GetMapping("/{addressId}")
	public ResponseEntity getAddressById(@PathVariable("addressId") UUID addressId) {
		return new ResponseEntity<AddressDto>(addressService.getAddressById(addressId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity saveNewAddress(@RequestBody AddressDto addressDto) {
		AddressDto savedDto = addressService.saveNewAddress(addressDto);

		/*
		String host = ServletUriComponentsBuilder.fromRequestUri(request)
				.replacePath(null)
				.build()
				.toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/addresses/" + savedDto.getId().toString());
		*/
		
		return new ResponseEntity<AddressDto>(savedDto, HttpStatus.CREATED);
	}

	@PutMapping("/{addressId}")
	public ResponseEntity updateAddressById(@RequestBody AddressDto addressDto, @PathVariable("addressId") UUID addressId) {
		addressService.updateAddressById(addressDto, addressId);
		return new ResponseEntity<AddressDto>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity deleteAddressById(@PathVariable("addressId") UUID addressId) {
		log.info("eliminando dirección");
		addressService.deleteAddressById(addressId);
		return new ResponseEntity<AddressDto>(HttpStatus.NO_CONTENT);
	}
	
	
}
