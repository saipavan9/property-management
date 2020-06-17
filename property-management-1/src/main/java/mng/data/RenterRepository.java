package mng.data;

import org.springframework.data.repository.CrudRepository;

import mng.model.Renter;

public interface RenterRepository extends CrudRepository<Renter, Long> {

	Renter findByUsername(String username);
	
	Renter findByEmail(String email);
	
	Boolean existsRenterByUsername(String username);
	
	Boolean existsRenterByEmail(String email);
	
}
