package mng.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import mng.data.RenterRepository;
import mng.data.TenantRepository;
import mng.forms.RegistrationForm;


@Controller
@RequestMapping("/register")
public class RegistrationController {

	
	private RenterRepository renterRepo;
	private TenantRepository tenantRepo;
	private BCryptPasswordEncoder passwordEncoder;
	
	  public RegistrationController(
		     RenterRepository renterRepo, TenantRepository tenantRepo,BCryptPasswordEncoder passwordEncoder) {
		   this.renterRepo = renterRepo;
		   this.tenantRepo = tenantRepo;
		    this.passwordEncoder = passwordEncoder;
		  }
		  
		  @GetMapping
		  public String registerForm() {
		    return "register";
		  }
		  
		  @PostMapping("/renter")
		  public String processRenter(RegistrationForm form) {			  
			  renterRepo.save(form.toRenter(passwordEncoder));
		    return "redirect:/login/renter";
		  }
		  
		  
		  @PostMapping("/tenant")
		  public String processTenant(RegistrationForm form) {
			  tenantRepo.save(form.toTenant(passwordEncoder));
		    return "redirect:/tenant/login";
		  }
		  

		  
}
