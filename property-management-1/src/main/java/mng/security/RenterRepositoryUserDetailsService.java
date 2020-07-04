package mng.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mng.data.RenterRepository;
import mng.model.Renter;

@Service
public class RenterRepositoryUserDetailsService implements UserDetailsService {

	@Autowired
	private RenterRepository renterRepo;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Renter renter;
		if(username != null) {
			
		   renter =  renterRepo.findByUsername(username);
			if(renter != null) return renter;
			
			renter = renterRepo.findByEmail(username);
			if(renter != null) return renter;
		}
		 throw new UsernameNotFoundException(
                 "User '" + username + "' not found");
	
	}
	
}
