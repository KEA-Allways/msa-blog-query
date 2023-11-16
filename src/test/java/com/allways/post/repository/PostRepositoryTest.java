package com.allways.post.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.allways.domain.category.domain.Category;
import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostQueryRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

	@Autowired
	PostQueryRepository postQueryRepository;
	@PersistenceContext
	EntityManager em;

	Category category;

	@Test
	void findTop10ByOrderByCreatedAtDesc() {
		// given // when
		List<Post> posts = postQueryRepository.findTop10ByOrderByCreatedAtAsc();

		// then

		for(int i=0; i< 10; i++) {
			Assertions.assertEquals(i+1, posts.get(i).getPostSeq());
		}


	}

	void clear() {
		em.flush();
		em.clear();
	}
}
