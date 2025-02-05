package fonepay.ecommerce.ecomerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fonepay.ecommerce.ecomerce.Service.ProductService;
import fonepay.ecommerce.ecomerce.dto.ProductDto;

@RestController
@RequestMapping("/api/products")
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping
	public ResponseEntity<List<?>> getAllProduct(){
		return service.getAll();
	}

	@GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getProductById(id));
    }
}
