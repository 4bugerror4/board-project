package com.bug.board.controller;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bug.board.domain.Board;
import com.bug.board.service.BoardService;
import com.bug.board.validator.BoardValidator;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;
	private final BoardValidator boardBalidator;

	@Transactional(readOnly = true)
	@GetMapping("/write")
	public String write(Model model) {

		model.addAttribute("board", new Board());

		return "board/write";
	}

	@Transactional(readOnly = true)
	@GetMapping("/detail/{id}")
	public String boardDetail(@PathVariable Long id, Model model) {

		Board board = boardService.findById(id);
		model.addAttribute("board", board);

		return "board/detail";

	}

	@Transactional
	@PostMapping("/write")
	public String writeSubmit(@Valid Board board, BindingResult bindingResult) {

		boardBalidator.validate(board, bindingResult);

		if (bindingResult.hasErrors()) {
			return "board/write";
		}

		board.setCreateDate(Timestamp.valueOf(getDateTime()));
		boardService.save(board);

		return "redirect:/";
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/modify/{id}")
	public String boardModify(@PathVariable Long id, Model model) {
		
		Board board = boardService.findById(id);
		model.addAttribute("board", board);

		return "board/modify";
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
