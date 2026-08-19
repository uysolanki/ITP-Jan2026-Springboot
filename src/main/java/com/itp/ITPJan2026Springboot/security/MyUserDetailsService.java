package com.itp.ITPJan2026Springboot.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itp.ITPJan2026Springboot.entity.MyUser;
import com.itp.ITPJan2026Springboot.repository.UserRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepo.findByUsername(username);
		if(user==null)
			throw new UsernameNotFoundException ("User does not exist");
		
		return new MyUserDecoratorWithRoles(user);
	}

}
