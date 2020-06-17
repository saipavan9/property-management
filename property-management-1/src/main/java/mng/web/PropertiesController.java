package mng.web;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import mng.data.PropertyRepository;
import mng.data.RenterRepository;
import mng.model.Renter;
import mng.model.RenterUnit;

@Controller
public class PropertiesController {

	@Autowired
	private PropertyRepository propertyRepo;
	
	@Autowired
	private RenterRepository renterRepo;
	
	@PostMapping("/propertySave")
	public String saveProperty(Principal principal,HttpServletRequest request) {
		
		RenterUnit renterUnit = new RenterUnit();
		renterUnit.setPropertyName(request.getParameter("propertyName"));
		renterUnit.setAddress(request.getParameter("address"));
		renterUnit.setNoOfbeds(Integer.parseInt(request.getParameter("noOfbeds")));
		renterUnit.setSizeOfProperty(request.getParameter("sizeOfProperty"));
		
		Renter renter = renterRepo.findByUsername(principal.getName());
		
		renterUnit.setRenter(renter);
		propertyRepo.save(renterUnit);
		List<RenterUnit> props = renter.getProperties();
		if(props == null) props = new ArrayList<>();
		props.add(renterUnit);
		
		renter.setProperties(props);
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String display(Principal principal, ModelMap model,HttpServletRequest request) {
		
		int page = 0; 
        int size = 8;
        
        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        
		
		Renter renter = renterRepo.findByUsername(principal.getName());
		Page<RenterUnit> props = propertyRepo.findByRenter(renter,PageRequest.of(page, size));
		

		model.addAttribute("properties",props);
		
		return "home";
	}
}
