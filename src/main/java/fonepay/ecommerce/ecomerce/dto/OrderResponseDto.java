package fonepay.ecommerce.ecomerce.dto;

import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private Long id;
    private Long userId;
    private List<ProductDto> products;
    private Double totalPrice;
    private OrderStatus orderStatus;
}
