package mng.web;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import mng.data.PropertyRepository;
import mng.data.RenterRepository;
import mng.data.TenantRepository;
import mng.model.Renter;
import mng.model.RenterUnit;
import mng.model.Tenant;

@Controller
public class HomeController {


	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private RenterRepository renterRepo;
	
	@Autowired
	private TenantRepository tenantRepo;
	
	
	@GetMapping("/renter")
	public String display(Principal principal, ModelMap model,HttpServletRequest request) {
		
		return "home";
	}
	
	@GetMapping(value = "/all",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String,String>> getAll(){
	
		Map<String,String> hm = new HashMap<>();
		for(Tenant t: tenantRepo.findAll()) {
			hm.put(String.valueOf(t.getTenant_id()), t.getFirstName()+" "+t.getLastName());
		}
		try {
			return new ResponseEntity<Map<String,String>>(hm,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Map<String,String>>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/tenant")
	public String getHome() {
		return "tenant/home";
	}
	
	
}
