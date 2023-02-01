package com.bug.board.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.board.domain.Board;
import com.bug.board.domain.User;
import com.bug.board.dto.ReplyAjaxRequestDto;
import com.bug.board.dto.ReplySaveRequestDto;
import com.bug.board.service.BoardService;
import com.bug.board.service.ReplyService;
import com.bug.board.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class ReplyRestController {
	
	private final BoardService boardService;
	private final UserService userService;
	private final ReplyService replyService;
	
	@PostMapping("/save")
	public void save(@RequestBody ReplyAjaxRequestDto dto, ReplySaveRequestDto saveDto) {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		Board board = boardService.findById(dto.getId());
		User user = userService.findByUsername(authentication.getName());
		
		replyService.save(saveDto.toEntity(dto.getContent(), user, board));
		
	}
	
	@DeleteMapping("/{replyId}")
	public void replyDelete(@PathVariable Long replyId) {
		System.out.println("reply Delete()");
		System.out.println(replyId);
		replyService.deleteById(replyId);
	}

}
