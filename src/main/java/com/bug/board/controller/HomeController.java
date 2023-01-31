package com.bug.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bug.board.auth.PrincipalUserDetails;
import com.bug.board.domain.Board;
import com.bug.board.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {
	
	private final BoardService boardService;
	
	@Transactional(readOnly = true)
	@GetMapping({"/", ""})
	public String home(Model model,@PageableDefault(size=5, sort="id", direction = Direction.DESC) Pageable pageable, @RequestParam(defaultValue = "", required = false) String searchText, @AuthenticationPrincipal PrincipalUserDetails principal) {
//		List<Board> boards = boardService.findAll();
//		Page<Board> boards = boardService.findAll(pageable);
		Page<Board> boards = boardService.findByTitleContainingOrContentContaining(pageable, searchText, searchText);
		
		if (principal != null) {
			System.out.println(principal.getUser().getRoleType());
		} else {
			System.out.println("======= Null");
		}
		
		int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
		int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
		
		model.addAttribute("principal", principal);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("boards", boards);
		
		return "index";
	}

}
