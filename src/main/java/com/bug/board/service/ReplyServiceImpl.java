package com.bug.board.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;

import com.bug.board.domain.Reply;
import com.bug.board.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

	private final ReplyRepository replyRepository;

	@Override
	public Reply save(Reply reply) {

		reply.setCreateDate(Timestamp.valueOf(getDateTime()));
		return replyRepository.save(reply);
	}
	
	@Override
	public void deleteById(Long id) {
		replyRepository.deleteById(id);
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
