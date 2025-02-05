package fonepay.ecommerce.ecomerce.Entity;

import java.util.Date;

import fonepay.ecommerce.ecomerce.Enum.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tbl_payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	private Order order;

	private double amount;

	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	

    @Temporal(TemporalType.DATE)
    private Date paymentDate;

	public Payment() {
	}

}
