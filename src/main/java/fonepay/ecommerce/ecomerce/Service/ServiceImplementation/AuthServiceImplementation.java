package fonepay.ecommerce.ecomerce.Service.ServiceImplementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Repository.UserRepository;
import fonepay.ecommerce.ecomerce.Security.SecurityJwt.JwtUtils;
import fonepay.ecommerce.ecomerce.Security.SecurityService.UserDetailsImp;
import fonepay.ecommerce.ecomerce.Service.AuthService;
import fonepay.ecommerce.ecomerce.dto.JwtResponse;
import fonepay.ecommerce.ecomerce.dto.LoginFormDto;
import jakarta.validation.Valid;

@Service
public class AuthServiceImplementation implements AuthService {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Override
	public ResponseEntity<?> login(@Valid LoginFormDto dto) {
		Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(auth);
		UserDetailsImp userDetails = (UserDetailsImp) auth.getPrincipal();
		String token = jwtUtils.generateJwtToken(auth);
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(token, 
				userDetails.getId(),
				userDetails.getEmail(),
				userDetails.getFirstname(),
				userDetails.getMiddlename(),
				userDetails.getLastname()
				));
	}

}
