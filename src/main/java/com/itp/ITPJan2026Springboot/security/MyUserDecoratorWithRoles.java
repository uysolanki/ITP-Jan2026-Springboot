package com.itp.ITPJan2026Springboot.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.itp.ITPJan2026Springboot.entity.MyUser;
import com.itp.ITPJan2026Springboot.entity.Role;

public class MyUserDecoratorWithRoles implements UserDetails {
	
	MyUser user;
	public MyUserDecoratorWithRoles(MyUser user)
	{
		this.user=user;
	}
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() 
    {
        List<Role> roles = user.getRoles();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolename()));
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

}
