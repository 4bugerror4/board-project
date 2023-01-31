package com.bug.board.service;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bug.board.domain.Board;
import com.bug.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;

	@Override
	public List<Board> findAll() {
		return boardRepository.findAll();
	}
	
	@Override
	public Board findById(Long id) {
		return boardRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 아이디는 존재 하지 않습니다. : " + id);
			}
		});
	}

	@Override
	public Board save(Board board) {
		return boardRepository.save(board);
	}

	@Override
	public void deleteById(Long id) {
		if (id == null) {
			throw new IllegalArgumentException("해당 아이디는 존재하지 않습니다. : " + id);
		}
		
		boardRepository.deleteById(id);
		
	}

	@Override
	public Page<Board> findAll(Pageable pageable) {
		return boardRepository.findAll(pageable);
	}

	@Override
	public Page<Board> findByTitleContainingOrContentContaining(Pageable pageable, String title, String content) {
		return boardRepository.findByTitleContainingOrContentContaining(pageable, title, content);
	}


}
