package demo.microservices.cliente.models;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
	private UUID id;
	private Integer version;
	private OffsetDateTime createdDate;
	private OffsetDateTime lasModifiedDate;

	private String productName;

	private ProductStyleEnum productStyle;

	private Long upc; // Universal Product Code

	private BigDecimal price;

	private Integer quantityOnHand;
}
