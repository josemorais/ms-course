package com.dev.oauth.services;

import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dev.oauth.entities.User;
import com.dev.oauth.feignclients.UserFeignClient;

@Service
public class UserService implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserFeignClient userFeignClient;

	public User findByEmail(String email) {
		User user = userFeignClient.findByEmail(email).getBody();
		if (Objects.isNull(user)) {
			logger.error("Email not found: " + email);
			throw new IllegalArgumentException("Email not found");
		}
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userFeignClient.findByEmail(username).getBody();
		if (Objects.isNull(user)) {
			logger.error("Email not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}

}
