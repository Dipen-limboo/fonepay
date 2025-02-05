package fonepay.ecommerce.ecomerce.dto;

import java.util.Date;

import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import fonepay.ecommerce.ecomerce.Enum.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSummaryDto {
	private Long orderId;
	private double totalAmount;
	private OrderStatus status;
	private Date orderDate;
	private PaymentStatus paymentStatus;
    private Date paymentDate;
}
