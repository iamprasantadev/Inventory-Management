package com.example.demo.service;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer userid;
	private String username;
	
	private Collection<? extends GrantedAuthority> authorities;
	@JsonIgnore
	private String password;
	 public UserDetailsImpl( Integer userid,String username, String password) {
		 	this.userid=userid;
		    this.username = username;
		    this.password = password;
		    //this.authorities = authorities;
		  }
	 public static UserDetailsImpl build(User user) {
////		    List<GrantedAuthority> authorities = user.getRoles().stream()
////		        .map(role -> new SimpleGrantedAuthority(role.getName().name()))
////		        .collect(Collectors.toList());
//
		    return new UserDetailsImpl(user.getUserid(),
		    		user.getUsername(), 
		    		user.getPassword()
		       );
		  }


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}


	public Integer getUserid() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
