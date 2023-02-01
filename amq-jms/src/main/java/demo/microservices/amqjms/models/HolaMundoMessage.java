package demo.microservices.amqjms.models;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HolaMundoMessage implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String message;

}
