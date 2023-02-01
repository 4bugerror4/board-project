package com.bug.board.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyAjaxRequestDto {
	
	private Long id;
	private String content;

}
