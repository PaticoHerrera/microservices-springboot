package demo.microservices.products.models;

import java.math.BigDecimal;
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
	private String productName;
	private Long upd; // Universal Product Code (alt. a SKU)
	private BigDecimal price;
	private Integer quantity;
}
