package com.EmployeeApp.security;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.rmi.registry.Registry;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.EmployeeApp.model.Employee;
import com.EmployeeApp.repository.EmployeeRepository;
import com.EmployeeApp.services.CustomUserDetailsService;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

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
	
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//    
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//            // ... configure authentication and authorization
//            .userDetailsService(userDetailsService);
//        
//        return http.build();
//    }
    
    
    @Bean
    public AuthenticationManager authenticationManager(
        CustomUserDetailsService userDetailsService,
        PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);

    }
    
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
	

	
    @Bean
    public SecurityFilterChain defaultFilterChain(HttpSecurity http) throws Exception {
    	return http
	        	.csrf(AbstractHttpConfigurer::disable)	
	        	.authorizeHttpRequests(registry->{   
		    	    registry.requestMatchers("/error", "/register/**", "/updateEmployee/**", "/login", "/forgotpassword").permitAll();
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
//    @Bean
//    public static JavaMailSenderImpl javaMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

//        javaMailSender.setProtocol("smtp");
//        javaMailSender.setHost("smtp.gmail.com");
//        javaMailSender.setPort(587);
//        
//        javaMailSender.setUsername("**********");
//        javaMailSender.setPassword("*********");
        
//        Properties mailProperties = new Properties();
//        mailProperties.put("mail.smpt.starttls.enable", true);
//        mailProperties.put("mail.smtp.starttls.required", true);
//        mailProperties.put("mail.smtp.auth", true);
//        
//        javaMailSender.setJavaMailProperties(mailProperties);
//
//        return javaMailSender;
//    }
	

	
//

  
//  @Bean
//  public UserDetailsServiceImpl implements UserDetailsService {
//
//      @Autowired
//      private EmployeeRepository employeeRepository;
//      
//
//
//      public UserDetails loadUserByUsernameDetails(String username) throws UsernameNotFoundException {
//          Employee employee = employeeRepository.findByUsername(username);
//          
//          Collection<GrantedAuthority> authorities = Arrays.asList(
//          		new SimpleGrantedAuthority(employee.getRole().toString())
//          );
//          if (employee != null) {
//              return new org.springframework.security.core.userdetails.User(
//            		  employee.getUsername(),
//            		  employee.getPassword(),
//            		  authorities
//                      // Get authorities or roles from your user entity
//            		  );
//          } else {
//              throw new UsernameNotFoundException("User not found with username: " + username);
//          }
//      }
//  }
  

    
}
