package mng.forms;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import lombok.Data;
import mng.model.Renter;
import mng.model.Tenant;

@Data
public class RegistrationForm {

	private String firstName;	
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String mobile;
	private String address;
	
	  public Renter toRenter(BCryptPasswordEncoder passwordEncoder) {
		    return new Renter(
		    		username,email,passwordEncoder.encode(password),firstName,lastName,address,mobile);
		  }
	  
	  
	  public Tenant toTenant(BCryptPasswordEncoder passwordEncoder) {
		    return new Tenant(
		    		username,email,passwordEncoder.encode(password),firstName,lastName,mobile);
		  }
	  
	  
}
