package fonepay.ecommerce.ecomerce.dto;

import java.util.Map;

import fonepay.ecommerce.ecomerce.Entity.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SalesMetricsDto {

	private long totalOrders;
	
	private double totalPayments;
	
	private Map<Product, Long> mostOrderedProducts;
}
