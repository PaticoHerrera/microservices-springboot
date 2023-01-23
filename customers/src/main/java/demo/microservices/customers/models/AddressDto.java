package demo.microservices.customers.models;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {
	
	private UUID id;
	private String direccion;
	private String ciudad;
	private UUID customerId;
}
