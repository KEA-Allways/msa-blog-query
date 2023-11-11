package com.allways.domain.reply.repository;

import com.allways.domain.reply.domain.Reply;
import com.allways.domain.reply.dto.ReplyDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Query("select r from Reply r where r.post.postSeq = :postSeq " +
            "order by r.createdAt asc nulls first ")
    List<Reply> findAllRepliesByPostSeq(@Param("postSeq") Long postSeq);
}
