package com.execlr.CustomerManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.execlr.CustomerManagement.entity.User;
import com.execlr.CustomerManagement.repository.UserRepository;

@Service
public class MyExcelrFetchPrincipalFromDatabaseAndDecorate implements UserDetailsService {

	@Autowired
	UserRepository userrepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user=userrepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException("User Not Found in Database");
		
		
		return new MyExcelrDecorator(user);
	}

}
