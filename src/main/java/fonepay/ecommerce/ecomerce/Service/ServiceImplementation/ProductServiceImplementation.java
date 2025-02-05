package fonepay.ecommerce.ecomerce.Service.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.Product;
import fonepay.ecommerce.ecomerce.Repository.ProductRepo;
import fonepay.ecommerce.ecomerce.Service.ProductService;
import fonepay.ecommerce.ecomerce.dto.ProductDto;

@Service
public class ProductServiceImplementation implements ProductService{

	@Autowired
	ProductRepo repo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public ResponseEntity<List<?>> getAll() {
		List<ProductDto> productDtos = repo.findAll().stream()
	            .map(product -> modelMapper.map(product, ProductDto.class))
	            .collect(Collectors.toList());
	    
	    return ResponseEntity.ok(productDtos);
	}

	@Override
    public ProductDto getProductById(Long id) {
        Product product = repo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return modelMapper.map(product, ProductDto.class);
    }

}
