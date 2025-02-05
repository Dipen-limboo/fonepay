package fonepay.ecommerce.ecomerce.Entity;

import java.util.Date;
import java.util.List;

import fonepay.ecommerce.ecomerce.Enum.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tbl_order")
@Getter
@Setter
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private User user;

	@OneToMany
	private List<OrderItem> orderItems;

	private double totalAmount;
	
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	@Enumerated(EnumType.STRING)
	private OrderStatus status;

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

}
