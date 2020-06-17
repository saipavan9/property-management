package mng.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import mng.data.RenterRepository;
import mng.data.TenantRepository;

@RestController
public class ValidationController {

	
	private RenterRepository renterRepo;
	private TenantRepository tenantRepo;
	
	public ValidationController(RenterRepository renterRepo, TenantRepository tenantRepo) {
		   this.renterRepo = renterRepo;
		   this.tenantRepo = tenantRepo;
	}
	
	  @PostMapping("/validEmail")
	  public String validEmail(String email,String type) {
		  
		  if(type.equals("renter"))
			  return renterRepo.existsRenterByEmail(email).toString();
		  
		  return tenantRepo.existsTenantByEmail(email).toString();
	  }
	  
	  
	  @PostMapping("/validUsername")
	  public String validUsername(String username,String type) {
		  
		  if(type.equals("renter"))
			  return renterRepo.existsRenterByUsername(username).toString();
		  
		  return tenantRepo.existsTenantByUsername(username).toString();
	  }
	  
	  
}
