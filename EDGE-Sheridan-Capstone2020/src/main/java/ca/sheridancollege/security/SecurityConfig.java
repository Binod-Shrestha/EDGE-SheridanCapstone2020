package ca.sheridancollege.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		// add this line of code to send data to h2 database
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.authorizeRequests();
		//Defined URL's and who has access
		// begins with specific to general
		http.authorizeRequests()
		// restriction
		.antMatchers("/user/**").hasAnyRole("USER")
		.antMatchers("/admin/**").hasAnyRole("ADMIN")
		.antMatchers("/basic/**").hasAnyRole("BASIC")
		.antMatchers("/rise/**").hasRole("RISE")
		.antMatchers("/soar/**").hasRole("SOAR")
				 
		// accessible by all
		.antMatchers("/","/images/**","/css/**","/javascripts/**","/**").permitAll()
		//add it for database security
		.antMatchers("/h2-console/**").permitAll()
		.anyRequest().authenticated()
		
		//Define our login page
		.and()
		.formLogin()
		.loginPage("/login") //url from controller
		.permitAll()
		.and()
		.logout()
		.invalidateHttpSession(true)
		.clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout") // default from spring boot
		.permitAll()
		// add the access denied handler
				.and()
				.exceptionHandling()
				.accessDeniedHandler(accessDeniedHandler);
			}
	// from Userdetails = 6 step
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	// step-7
	@Autowired
	public UserDetailsServiceImpl userDetailsService;	
	
	
	//step-8
	  @Autowired public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
	  
	  auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	  
	  }
	 
	

}
