package fonepay.ecommerce.ecomerce.dto;

public class LoginFormDto {
	private String email;
	private String password;
	public LoginFormDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginFormDto(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
