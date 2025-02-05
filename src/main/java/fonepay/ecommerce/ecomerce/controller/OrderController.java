package fonepay.ecommerce.ecomerce.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.Payment;
import fonepay.ecommerce.ecomerce.Entity.User;
import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import fonepay.ecommerce.ecomerce.Repository.UserRepository;
import fonepay.ecommerce.ecomerce.Security.SecurityService.UserDetailsImp;
import fonepay.ecommerce.ecomerce.Service.OrderService;
import fonepay.ecommerce.ecomerce.Service.PaymentService;
import fonepay.ecommerce.ecomerce.dto.OrderRequestDto;
import fonepay.ecommerce.ecomerce.dto.OrderSummaryDto;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentService paymentService;
    
    @Autowired
    private UserRepository userRepo;

    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderRequestDto requestDto, Authentication auth) {
        try {
            orderService.placeOrders(requestDto, auth);
            return ResponseEntity.ok("Order placed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error placing order: " + e.getMessage());
        }
    }

    @GetMapping("/summary")
    public ResponseEntity<List<OrderSummaryDto>> getOrderSummary(Authentication auth) {
        UserDetailsImp userDetails = (UserDetailsImp) auth.getPrincipal();
        Optional<User> user = userRepo.findById(userDetails.getId());

        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        List<OrderSummaryDto> orderSummary = orderService.getOrderSummary(user.get());
        return ResponseEntity.ok(orderSummary);
    }

    @PostMapping("/pay/{orderId}")
    public ResponseEntity<?> processPayment(@PathVariable Long orderId, Authentication auth) {
        try {
            Optional<Order> orderOpt = orderService.getOrderById(orderId);
            if (orderOpt.isEmpty()) {
                return ResponseEntity.badRequest().body("Order not found!");
            }

            Order order = orderOpt.get();

            if (order.getStatus() == OrderStatus.PAID) {
                return ResponseEntity.badRequest().body("Payment already made for this order.");
            }

            double totalAmount = order.getTotalAmount();
            Payment payment = paymentService.processPayment(order, totalAmount);

            return ResponseEntity.ok(payment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error processing payment: " + e.getMessage());
        }
    }
}
