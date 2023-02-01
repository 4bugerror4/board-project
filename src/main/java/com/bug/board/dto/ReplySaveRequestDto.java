package com.bug.board.dto;

import com.bug.board.domain.Board;
import com.bug.board.domain.Reply;
import com.bug.board.domain.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReplySaveRequestDto {
	
	private String content;
	private User user;
	private Board board;
	
	public Reply toEntity(String content, User user, Board board) {
		return Reply.builder()
				.content(content)
				.user(user)
				.board(board)
				.build();
	}

}
