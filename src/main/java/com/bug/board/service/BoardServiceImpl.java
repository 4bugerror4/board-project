package com.bug.board.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bug.board.domain.Board;
import com.bug.board.domain.User;
import com.bug.board.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository boardRepository;
	private final UserService userService;

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
	public Board save(Board reqBoard) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		User user = userService.findByUsername(auth.getName()).orElseThrow(new Supplier<UsernameNotFoundException>() {

			@Override
			public UsernameNotFoundException get() {
				return new UsernameNotFoundException("해당 아이디는 존재하지 않습니다. : " + auth.getName());
			}
		});
		
		reqBoard.setUser(user);
		reqBoard.setCreateDate(Timestamp.valueOf(getDateTime()));
		Board board = boardRepository.save(reqBoard);
		
		return board;
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
	
	private String getDateTime() {

		LocalDate nowDate = LocalDate.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formatedNowDate = nowDate.format(formatterDate);

		LocalTime nowTime = LocalTime.now();
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		String formatedNowTime = nowTime.format(formatterTime);

		String dateTime = formatedNowDate + " " + formatedNowTime;

		return dateTime;
	}


}
