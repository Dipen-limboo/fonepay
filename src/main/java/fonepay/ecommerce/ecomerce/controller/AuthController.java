package fonepay.ecommerce.ecomerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fonepay.ecommerce.ecomerce.Service.AuthService;
import fonepay.ecommerce.ecomerce.dto.LoginFormDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	@Autowired
	AuthService authService;
	
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUSer(@Valid @RequestBody LoginFormDto dto){
		return authService.login(dto);
	}
}
