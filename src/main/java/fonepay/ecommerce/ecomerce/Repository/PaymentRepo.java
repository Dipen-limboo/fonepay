package fonepay.ecommerce.ecomerce.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long>{

	List<Payment> findByPaymentDateAfter(Date date);

	Payment findByOrder(Order order);

}
