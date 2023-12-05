package com.allways.domain.category.repository;

import com.allways.domain.category.entity.Category;
import com.allways.domain.theme.entity.Theme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class CategoryRepositoryTest {
    @Autowired private EntityManager entityManager;
    @Autowired private CategoryRepository categoryRepository;

    @Test
    @Transactional
    public void findAllCategoriesWithThemesByThemeSeqTest() {
        // given
        Long themeSeq = 1L;
        Theme theme = new Theme("themeName", 1L, 1L);
        Category category = new Category(
                "categoryName",
                1L,
                themeSeq,
                theme);
        entityManager.persist(theme);
        entityManager.persist(category);
        entityManager.flush();

        // when
        List<Category> categories = categoryRepository
                .findAllCategoriesWithThemesByThemeSeq(themeSeq);

        // then
        assertNotNull(categories);
        assertFalse(categories.isEmpty());

        categoryRepository.deleteById(category.getCategorySeq());
    }

    @Test
    @Transactional
    public void findByCategorySeqTest() {
        // given - junit DB에 실제 존재하는 CategorySeq 선택
        Long categorySeq = 1L;

        // when
        Optional<Category> foundCategory = categoryRepository
                .findByCategorySeq(categorySeq);

        // then
        assertTrue(foundCategory.isPresent());
    }

    @Test
    public void readLastCategoryOrderByThemeSeqTest() {
        // given - 실제 junitDB에 존재하는 themeSeq 선택
        Long themeSeq = 1L;

        // when
        Long lastCategoryOrder = categoryRepository.readLastCategoryOrderByThemeSeq(themeSeq);

        // then
        assertNotNull(lastCategoryOrder);
    }
}
