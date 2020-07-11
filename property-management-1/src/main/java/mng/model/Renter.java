package mng.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@ToString
public class Renter implements UserDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long renter_id;
	
	@Column(unique = true)
	private final String username;
	
	@Column(unique = true)
	private final String email;
	
	private final String password;
	
	private final String firstName;
	
	private final String lastName;
	
	private final String address;
	
	private final String mobile;
	
	
	@OneToMany(mappedBy = "renter",fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
	private List<RenterUnit> properties = new ArrayList<>();
	
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
