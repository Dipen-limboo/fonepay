package fonepay.ecommerce.ecomerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fonepay.ecommerce.ecomerce.Entity.OrderItem;

public interface OrderItemRepo extends JpaRepository<OrderItem, Long>{

}
