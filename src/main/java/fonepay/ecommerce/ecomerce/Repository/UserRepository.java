package fonepay.ecommerce.ecomerce.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fonepay.ecommerce.ecomerce.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Query("Select u from User u where u.email =: email")
	Optional<User> findByEmail(String email);

}
