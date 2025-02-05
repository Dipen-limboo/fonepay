package fonepay.ecommerce.ecomerce.dto;

public class JwtResponse {
	private String token;
	private Long id;
	private String Email;
	private String firstName;
	private String middleName;
	private String lastName;
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JwtResponse(String token, Long id, String email, String firstName, String middleName, String lastName) {
		super();
		this.token = token;
		this.id = id;
		Email = email;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}
	
	
}
