package com.bug.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long>{
	
	// 게시판 검색 찾기 JPA Naming Query 작성
	Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
}
