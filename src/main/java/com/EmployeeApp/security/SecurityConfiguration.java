package com.EmployeeApp.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.AuthorizeRequestsDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain defaultFilterChain(HttpSecurity httpSecurity) throws Exception {
//        return httpSecurity
//                .csrf(csrf-> csrf.disable())
//                .authorizeHttpRequests(auth-> auth.requestMatchers("/register","/error").permitAll()
//                .anyRequest().authenticated())
//                .httpBasic(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults())
//                .build();    
//    }
	

	
    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
    	return http
	        	.csrf(AbstractHttpConfigurer::disable)	
	        	.authorizeHttpRequests(registry->{   
		    	    registry.requestMatchers("/error", "/register/**", "/updateEmployee/**", "/login").permitAll();
	        		registry.requestMatchers("/").hasAnyRole("ADMIN", "USER");
	        		registry.requestMatchers("/**").hasRole("ADMIN");
	        		registry.anyRequest().authenticated();
		            
	        	})
//	        	.formLogin(Customizer.withDefaults())
		        .formLogin(formLogin -> formLogin
		        		.loginPage("/login")
		        		.permitAll()
		        		.defaultSuccessUrl("/", true))
//	        	.build();
	        	

		        .logout((logout) -> logout
		        		.clearAuthentication(true)       	
		        		.invalidateHttpSession(true)
		        		.deleteCookies("JSESSIONID"))
		        .build();
    }

	@Bean
	public PasswordEncoder passwordEncoder(){
	    return new BCryptPasswordEncoder();
	}
	
//
  @Bean
  public UserDetailsService userDetailsService() {
      UserDetails normalUser = User.builder()
              .username("user")
              .password(new BCryptPasswordEncoder().encode("password"))
              .roles("USER")
              .build();
      UserDetails adminUser = User.builder()
              .username("admin")
              .password(new BCryptPasswordEncoder().encode("password"))
              .roles("ADMIN")
              .build();
      return new InMemoryUserDetailsManager(normalUser, adminUser);
  }
  
//  @Bean
//  public CustomUserDetailsService implements UserDetailsService {
//
//      @Autowired
//      private EmployeeRepository employeeRepository;
//
//      @Override
//      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//          Optional<Employee> employee = employeeRepository.findByUsername(username);
//          if (employee.isPresent()) {
//              return new org.springframework.security.core.userdetails.User(
//            		  employee.get().getUsername(),
//            		  employee.get().getPassword(),
//                      // Get authorities or roles from your user entity
//                      new ArrayList<>());
//          } else {
//              throw new UsernameNotFoundException("User not found with username: " + username);
//          }
//      }
//  }
  

    
}
