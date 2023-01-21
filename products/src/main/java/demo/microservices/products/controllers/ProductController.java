package demo.microservices.products.controllers;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import demo.microservices.products.models.ProductDto;
import demo.microservices.products.services.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
	
	private ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping("/{productId}")
	public ResponseEntity<ProductDto> getProduct(@PathVariable("productId") UUID productId) {
		return new ResponseEntity<ProductDto>(productService.getProductById(productId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity handlePost(@RequestBody ProductDto productDto, HttpServletRequest request) {
		
		ProductDto savedDto = productService.saveNewProduct(productDto);
				
		String baseUrl = ServletUriComponentsBuilder.fromRequestUri(request)
				.replacePath(null)
				.build()
				.toUriString();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", baseUrl + "/api/v1/product/" + savedDto.getId().toString());
		return new ResponseEntity(savedDto, HttpStatus.CREATED);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity handlePut(@PathVariable("productId") UUID productId, ProductDto productDto) {
		productService.updateProduct(productId, productDto);
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity deleteProduct(@PathVariable("productId") UUID productId) {
		productService.deleteById(productId);

		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}	
}
