package fonepay.ecommerce.ecomerce.Service.ServiceImplementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.OrderItem;
import fonepay.ecommerce.ecomerce.Entity.Payment;
import fonepay.ecommerce.ecomerce.Entity.Product;
import fonepay.ecommerce.ecomerce.Entity.User;
import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import fonepay.ecommerce.ecomerce.Repository.OrderRepo;
import fonepay.ecommerce.ecomerce.Repository.PaymentRepo;
import fonepay.ecommerce.ecomerce.Repository.ProductRepo;
import fonepay.ecommerce.ecomerce.Repository.UserRepository;
import fonepay.ecommerce.ecomerce.Security.SecurityService.UserDetailsImp;
import fonepay.ecommerce.ecomerce.Service.OrderService;
import fonepay.ecommerce.ecomerce.dto.OrderRequestDto;
import fonepay.ecommerce.ecomerce.dto.OrderSummaryDto;

@Service
public class OrderServiceImplementation implements OrderService {

    @Autowired
    private OrderRepo orderRepository;

    @Autowired
    private ProductRepo productRepository;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private PaymentRepo paymentRepo;  

    @Override
    public void placeOrders(OrderRequestDto requestDto, Authentication auth) {
        List<Product> products = productRepository.findAllById(requestDto.getProductIds());
        UserDetailsImp userDetails = (UserDetailsImp) auth.getPrincipal();
        Optional<User> user = userRepo.findById(userDetails.getId());

        if (user.isEmpty()) {
            throw new RuntimeException("User not found!");
        }

        List<OrderItem> orderItems = new ArrayList<>();
        double totalAmount = 0.0;

        for (Product product : products) {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(1);
            orderItem.setPrice(product.getPrice());

            orderItems.add(orderItem);
            totalAmount += product.getPrice();
        }

        Order order = new Order();
        order.setUser(user.get());
        order.setOrderItems(orderItems);
        order.setStatus(OrderStatus.PENDING);
        order.setTotalAmount(totalAmount);
        order.setOrderDate(new Date());

        orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public List<OrderSummaryDto> getOrderSummary(User user) {
        List<Order> orders = orderRepository.findByUser(user);
        List<OrderSummaryDto> orderSummaryDtos = new ArrayList<>();

        for (Order order : orders) {
            OrderSummaryDto dto = new OrderSummaryDto();
            dto.setOrderId(order.getId());
            dto.setTotalAmount(order.getTotalAmount());
            dto.setStatus(order.getStatus());
            dto.setOrderDate(order.getOrderDate());

            Payment payment = paymentRepo.findByOrder(order);
            if (payment != null) {
                dto.setPaymentStatus(payment.getPaymentStatus());
                dto.setPaymentDate(payment.getPaymentDate());  
            }

            orderSummaryDtos.add(dto);
        }

        return orderSummaryDtos;
    }
}
