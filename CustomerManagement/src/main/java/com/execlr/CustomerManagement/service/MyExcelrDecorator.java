package com.execlr.CustomerManagement.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.execlr.CustomerManagement.entity.Role;
import com.execlr.CustomerManagement.entity.User;

public class MyExcelrDecorator implements UserDetails {

	User user=null;
	public MyExcelrDecorator(User user)
	{
		this.user=user;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName())); 
        }
         
        return authorities;

	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		LocalDate expirydate=user.getAccountExpiryDate();  //expirydate=30-Apr-2024
		LocalDate todaysdate=LocalDate.now();             // todaysdate=22-Apr-2024
		if(expirydate.isAfter(todaysdate))
			return true;
		else
			return false;
		
	}

	@Override
	public boolean isAccountNonLocked() {
		if(user.getAccountLockedStatus()==1)
			return true;
		else
			return false;	
	}

	@Override
	public boolean isCredentialsNonExpired() {
		LocalDate credexpirydate=user.getCredentialsExpiryDate();  //expirydate=30-Apr-2024
		LocalDate todaysdate=LocalDate.now();                      // todaysdate=22-Apr-2024
		if(credexpirydate.isAfter(todaysdate))
			return true;
		else
			return false;
	}

	@Override
	public boolean isEnabled() {
		if(user.getAccEnabledStatus()==1)
			return true;
		else
			return false;	
	}

}
