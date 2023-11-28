package com.allways.domain.post.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.allways.domain.post.entity.Post;

public interface PostQueryRepository extends JpaRepository<Post, Long> {
	List<Post> findTop10ByOrderByCreatedAtDesc();
	Page<Post> findAllByUserSeq(Long userSeq,Pageable pageable);
	Page<Post> findAllByUserSeqAndCategory_CategorySeqOrderByCreatedAt(Long userSeq,Long categorySeq,Pageable pageable);


}
