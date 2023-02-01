package com.bug.board.service;

import com.bug.board.domain.Reply;

public interface ReplyService {

	Reply save(Reply reply);

	void deleteById(Long id);

}
