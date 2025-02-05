package fonepay.ecommerce.ecomerce.Service;

import org.springframework.http.ResponseEntity;

import fonepay.ecommerce.ecomerce.dto.LoginFormDto;
import jakarta.validation.Valid;

public interface AuthService {

	ResponseEntity<?> login(@Valid LoginFormDto dto);
	
}
