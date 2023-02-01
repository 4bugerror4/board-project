package com.bug.board.service;

import java.util.Optional;

import com.bug.board.domain.User;

public interface UserService {
	
	User findByUsername(String username);
	
	User save(User user);

}
