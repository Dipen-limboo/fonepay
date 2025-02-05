package fonepay.ecommerce.ecomerce.Service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import fonepay.ecommerce.ecomerce.dto.ProductDto;

public interface ProductService {

	ResponseEntity<List<?>> getAll();

	ProductDto getProductById(Long id);


}
