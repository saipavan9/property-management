package mng.data;

import java.util.Map;

import org.springframework.data.repository.CrudRepository;

import mng.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
	
	Tenant findByUsername(String username);
	
	Tenant findByEmail(String email);
	
	
	Boolean existsTenantByUsername(String username);
	Boolean existsTenantByEmail(String email);

}
