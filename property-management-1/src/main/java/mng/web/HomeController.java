package mng.web;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import mng.data.PropertyRepository;
import mng.data.RenterRepository;
import mng.model.Renter;
import mng.model.RenterUnit;

@Controller
public class HomeController {


	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private RenterRepository renterRepo;
	
	
	@GetMapping("/renter")
	public String display(Principal principal, ModelMap model,HttpServletRequest request) {
		
		return "home";
	}
	
	
}
