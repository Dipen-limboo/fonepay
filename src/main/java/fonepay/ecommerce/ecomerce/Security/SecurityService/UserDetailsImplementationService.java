package fonepay.ecommerce.ecomerce.Security.SecurityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.User;
import fonepay.ecommerce.ecomerce.Repository.UserRepository;

@Service
public class UserDetailsImplementationService implements UserDetailsService{

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with " + email));
		return UserDetailsImp.build(user);
	}

}
