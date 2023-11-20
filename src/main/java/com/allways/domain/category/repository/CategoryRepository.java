package com.allways.domain.category.repository;

import com.allways.domain.category.domain.Category;
import com.allways.domain.category.dto.CategoryDto;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c LEFT JOIN FETCH c.theme t WHERE c.themeSeq = :themeSeq")
    List<Category> findAllCategoriesWithThemesByThemeSeq(@Param("themeSeq") Long themeSeq);

    Optional<Category> findByCategorySeq(Long categorySeq);
}
