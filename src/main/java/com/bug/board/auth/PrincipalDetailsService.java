package com.bug.board.auth;

import java.util.function.Supplier;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bug.board.domain.User;
import com.bug.board.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
	
	private final UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userService.findByUsername(username).orElseThrow(new Supplier<UsernameNotFoundException>() {

			@Override
			public UsernameNotFoundException get() {
				return new UsernameNotFoundException("해당 유저이름은 존재하지 않습니다. : " + username);
			}
		});
		
		return new PrincipalUserDetails(user);
	}

}
