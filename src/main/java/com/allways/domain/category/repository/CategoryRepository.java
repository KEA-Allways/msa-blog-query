package com.allways.domain.category.repository;

import com.allways.domain.category.domain.Category;
import com.allways.domain.category.dto.CategoryDto;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.theme t WHERE c.themeSeq = :themeSeq")
    List<Category> findAllCategoriesWithThemesByThemeSeq(@Param("themeSeq") Long themeSeq);

    Optional<Category> findByCategorySeq(@Param("categorySeq") Long categorySeq);

    @Query("select COALESCE(max(c.categoryOrder), 0) from Category c where c.themeSeq = :themeSeq")
    Long readLastCategoryOrderByThemeSeq(@Param("themeSeq") Long themeSeq);
}
