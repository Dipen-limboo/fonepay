package fonepay.ecommerce.ecomerce.Service.ServiceImplementation;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fonepay.ecommerce.ecomerce.Entity.Order;
import fonepay.ecommerce.ecomerce.Entity.OrderItem;
import fonepay.ecommerce.ecomerce.Entity.Payment;
import fonepay.ecommerce.ecomerce.Entity.Product;
import fonepay.ecommerce.ecomerce.Repository.OrderRepo;
import fonepay.ecommerce.ecomerce.Repository.PaymentRepo;
import fonepay.ecommerce.ecomerce.Service.SalesMetricsService;
import fonepay.ecommerce.ecomerce.dto.SalesMetricsDto;

@Service
public class SalesMetricsServiceImplementation implements SalesMetricsService{
	@Autowired
    private OrderRepo orderRepo;

    @Autowired
    private PaymentRepo paymentRepo;

    // Get total orders and payments for the past 7 days
    public SalesMetricsDto getSalesMetrics(String period) {
        Date startDate = getStartDateForPeriod(period);

        // Fetch orders and payments based on the period
        List<Order> orders = orderRepo.findByOrderDateAfter(startDate);
        List<Payment> payments = paymentRepo.findByPaymentDateAfter(startDate);

        double totalPayments = payments.stream().mapToDouble(Payment::getAmount).sum();
        long totalOrders = orders.size();
        Map<Product, Long> mostOrderedProducts = getMostOrderedProducts(orders);

        SalesMetricsDto metrics = new SalesMetricsDto();
        metrics.setTotalOrders(totalOrders);
        metrics.setTotalPayments(totalPayments);
        metrics.setMostOrderedProducts(mostOrderedProducts);
        return metrics;
    }

    private Date getStartDateForPeriod(String period) {
        Calendar calendar = Calendar.getInstance();
        switch (period) {
            case "daily":
                calendar.add(Calendar.DATE, -1);
                break;
            case "weekly":
                calendar.add(Calendar.DATE, -7);
                break;
            case "monthly":
                calendar.add(Calendar.MONTH, -1);
                break;
            default:
                throw new IllegalArgumentException("Invalid period");
        }
        return calendar.getTime();
    }

    private Map<Product, Long> getMostOrderedProducts(List<Order> orders) {
        Map<Product, Long> productCountMap = new HashMap<>();
        for (Order order : orders) {
            for (OrderItem item : order.getOrderItems()) {
                productCountMap.put(item.getProduct(), productCountMap.getOrDefault(item.getProduct(), 0L) + 1);
            }
        }
        return productCountMap;
    }
}
