package com.bug.board.api;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.board.domain.Board;
import com.bug.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardRestController {
	
	private final BoardService boardService;
	
	@Transactional(readOnly = true)
	@GetMapping({"", "/"})
	public List<Board> boardAll() {
		return boardService.findAll();
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public Board board(@PathVariable Long id) {
		return boardService.findById(id);
	}
	
	@Transactional
	@PostMapping("/write")
	public Board write(@RequestBody Board board) {
		return boardService.save(board);
	}
	
	@Transactional
	@PutMapping("/modify/{id}")
	public Board modify(@PathVariable Long id, @RequestBody Board reqBoard) {
		Board board = boardService.findById(id);
		
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
		board.setCreateDate(Timestamp.valueOf(getDateTime()));
		
		System.out.println(board);
		
		return board;
		
	}
	
	@Transactional
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable Long id) {
		boardService.deleteById(id);
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
