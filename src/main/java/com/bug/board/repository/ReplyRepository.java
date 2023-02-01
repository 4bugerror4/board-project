package com.bug.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.board.domain.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{

}
