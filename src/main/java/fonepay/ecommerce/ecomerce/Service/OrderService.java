package fonepay.ecommerce.ecomerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.User;
import fonepay.ecommerce.ecomerce.dto.OrderRequestDto;
import fonepay.ecommerce.ecomerce.dto.OrderSummaryDto;

@Service
public interface OrderService {

	void placeOrders(OrderRequestDto requestDto, Authentication auth);

	Optional<Order> getOrderById(Long orderId);

	List<OrderSummaryDto> getOrderSummary(User user);

}
