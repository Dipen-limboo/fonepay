package fonepay.ecommerce.ecomerce.Service;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.Payment;

public interface PaymentService {
    Payment processPayment(Order order, double totalAmount);
}

