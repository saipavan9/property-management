package mng.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsService RenterRepositoryUserDetailsService;
	
	
@Override
protected void configure(HttpSecurity http) throws Exception {
	
	http
	.authorizeRequests()
	.antMatchers("/css/**","/js/**","/register/**","/validEmail","/validUsername").permitAll()
	.anyRequest().authenticated()
	.and()
	.formLogin()
	.loginPage("/renter/login")
	.defaultSuccessUrl("/renter")
	.permitAll()
	.and()
	.logout()
	.logoutUrl("/renter/logout")
	.logoutSuccessUrl("/renter/login")
    .invalidateHttpSession(true)
    .deleteCookies("JSESSIONID")
    .permitAll()
    .and()
	.csrf().disable();

   
   http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
}


@Bean
@Primary
public BCryptPasswordEncoder encoder() {
  return new BCryptPasswordEncoder();
}


@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
	auth.userDetailsService(RenterRepositoryUserDetailsService).passwordEncoder(encoder());
}


}
