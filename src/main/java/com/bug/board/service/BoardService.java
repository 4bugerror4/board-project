package com.bug.board.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bug.board.domain.Board;

public interface BoardService {
	
	List<Board> findAll();
	
	Page<Board> findAll(Pageable pageable);
	
	Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String title, String content);
	
	Board findById(Long id);
	
	Board save(Board board);
	
	void deleteById(Long id);

}
