package fonepay.ecommerce.ecomerce.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderRequestDto {
    
	private List<Long> productIds;
}
