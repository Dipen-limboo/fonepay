package fonepay.ecommerce.ecomerce.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.User;

public interface OrderRepo extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);

	List<Order> findByOrderDateAfter(Date date);

}
