package fonepay.ecommerce.ecomerce.Service.ServiceImplementation;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.Payment;
import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import fonepay.ecommerce.ecomerce.Enum.PaymentStatus;
import fonepay.ecommerce.ecomerce.Repository.PaymentRepo;
import fonepay.ecommerce.ecomerce.Service.PaymentService;

@Service
public class PaymentServiceImplmentation implements PaymentService {
	@Autowired
	private PaymentRepo paymentRepository;

	@Override
	public Payment processPayment(Order order, double totalAmount) {
		Payment payment = new Payment();
		payment.setOrder(order);
		payment.setAmount(totalAmount);
		payment.setPaymentStatus(PaymentStatus.COMPLETED); 
		payment.setPaymentDate(new Date());
		paymentRepository.save(payment);

		order.setStatus(OrderStatus.PAID);
		return payment;
	}
}
