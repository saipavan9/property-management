package mng.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class TenantSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	private UserDetailsService TenantRepositoryUserDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.antMatcher("/tenant/**")
		.authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.loginPage("/tenant/login")
			.defaultSuccessUrl("/tenant")
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/tenant/logout")
			.logoutSuccessUrl("/renter/login").clearAuthentication(true)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
	    .	and()
		.csrf().disable();

	   
	   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
	}
	
	
	@Bean
	public BCryptPasswordEncoder encoder1() {
	  return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(TenantRepositoryUserDetailsService).passwordEncoder(encoder1());
	}
}
