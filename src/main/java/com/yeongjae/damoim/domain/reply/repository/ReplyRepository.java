package com.yeongjae.damoim.domain.reply.repository;

import com.yeongjae.damoim.domain.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
