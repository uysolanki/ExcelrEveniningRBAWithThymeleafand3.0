package com.execlr.CustomerManagement.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.execlr.CustomerManagement.service.MyExcelrFetchPrincipalFromDatabaseAndDecorate;

@Configuration
@EnableWebSecurity
public class MyWebSecurity //extends WebSecurityConfigurerAdapter
{

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception  //Authentication
//	{
//		auth.authenticationProvider(myExcelrAuthProvider());
//	}
	

	@Bean
	public DaoAuthenticationProvider myExcelrAuthProvider() 
	{
	DaoAuthenticationProvider mydao=new DaoAuthenticationProvider();
	mydao.setUserDetailsService(myExcelrUser());
	mydao.setPasswordEncoder(myExcelrPassword());
	return mydao;
	}

	@Bean
	public PasswordEncoder myExcelrPassword() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService myExcelrUser() {
		return new MyExcelrFetchPrincipalFromDatabaseAndDecorate();
	}


//	@Override
//	protected void configure(HttpSecurity http) throws Exception  //Authorisation
//	{
//		http.authorizeRequests()
//        .antMatchers("/","/readcustomerlist","/customers/addform","/403").hasAnyAuthority("USER","ADMIN")
//        .antMatchers("/customers/editform/**","/deletecustomer/**").hasAuthority("ADMIN")
//        .anyRequest().authenticated()
//        .and()
//        .formLogin().loginProcessingUrl("/login").successForwardUrl("/readcustomerlist").permitAll()
//        .and()
//        .logout().logoutSuccessUrl("/login").permitAll()
//        .and()
//        .exceptionHandling().accessDeniedPage("/403")  //if there is 403 status code
//        .and()
//        .cors().and().csrf().disable();
//	}
	
	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		 http.authorizeRequests()
	        .antMatchers("/","/readcustomerlist","/customers/addform","/403").hasAnyAuthority("USER","ADMIN")
	        .antMatchers("/customers/editform/**","/deletecustomer/**").hasAuthority("ADMIN")
	        .anyRequest().authenticated()
	        .and()
	        .formLogin().loginProcessingUrl("/login").successForwardUrl("/readcustomerlist").permitAll()
	        .and()
	        .logout().logoutSuccessUrl("/login").permitAll()
	        .and()
	        .exceptionHandling().accessDeniedPage("/403")  //if there is 403 status code
	        .and()
	        .cors().and().csrf().disable();
		 
		 http.authenticationProvider(myExcelrAuthProvider());
		 return http.build();
		 
	 }
	 
}
