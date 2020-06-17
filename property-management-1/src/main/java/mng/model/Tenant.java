package mng.model;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@NoArgsConstructor(force=true)
@RequiredArgsConstructor
//@Table(uniqueConstraints={@UniqueConstraint(columnNames={"username","email"})})
public class Tenant implements UserDetails{

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long tenant_id;
	
	@Column(unique = true)
	private final String username;
	
	@Column(unique = true)
	private final String email;
	
	private final String password;
	
	private final String firstName;
	
	private final String lastName;
	
	private final String mobile;
	
	@Override
	  public Collection<? extends GrantedAuthority> getAuthorities() {
	    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
	  }

	  @Override
	  public boolean isAccountNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isAccountNonLocked() {
	    return true;
	  }

	  @Override
	  public boolean isCredentialsNonExpired() {
	    return true;
	  }

	  @Override
	  public boolean isEnabled() {
	    return true;
	  }
}
