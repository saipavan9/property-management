package mng.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import mng.data.TenantRepository;
import mng.model.Tenant;



public class TenantRepositoryUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private TenantRepository tenantRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Tenant tenant;
		if(username != null) {
			
		   tenant =  tenantRepo.findByUsername(username);
			if(tenant != null) return tenant;
			
			tenant = tenantRepo.findByEmail(username);
			if(tenant != null) return tenant;
		}
		 throw new UsernameNotFoundException(
                 "User '" + username + "' not found");
	}
}
