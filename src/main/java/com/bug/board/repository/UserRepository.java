package com.bug.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.board.domain.Board;
import com.bug.board.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	// 유저이름으로 유저 찾기
	Optional<User> findByUsername(String username);
}
