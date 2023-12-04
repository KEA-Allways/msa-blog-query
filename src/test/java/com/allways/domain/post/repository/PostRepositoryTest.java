package com.allways.domain.post.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.allways.domain.post.entity.Post;
import com.allways.domain.post.repository.PostQueryRepository;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PostRepositoryTest {

	@Autowired
	PostQueryRepository postQueryRepository;
	@PersistenceContext
	EntityManager em;

	@Test
	void findTop10ByOrderByCreatedAtDesc() {
		// given // when
		List<Post> posts = postQueryRepository.findTop12ByOrderByCreatedAtDesc();

		// then
		for(int i = 0; i < 10; i++) {
			assertThat(posts.get(i)).isNotNull();
		}
	}

	@AfterEach
	void clear() {
		em.flush();
		em.clear();
	}
}
