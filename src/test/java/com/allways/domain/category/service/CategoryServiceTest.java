package com.allways.domain.category.service;

import com.allways.domain.category.entity.Category;
import com.allways.domain.category.dto.CategoryDto;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.theme.entity.Theme;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {
    @Mock private CategoryRepository categoryRepository;
    @InjectMocks private CategoryService categoryService;

    @Test
    public void readAllCategoriesWithThemesByThemeSeqTest() {
        // Given
        Long themeSeq = 1L;
        Theme theme = new Theme(
                "themeName",
                1L, 1L);
        Category category1 = new Category(
                "categoryName1",
                1L, 1L, theme);
        Category category2 = new Category(
                "categoryName2",
                2L, 1L, theme);
        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        when(categoryRepository.findAllCategoriesWithThemesByThemeSeq(themeSeq))
                .thenReturn(categories);

        // When
        List<Category> result = categoryService.readAll(themeSeq);

        // Then
        verify(categoryRepository).findAllCategoriesWithThemesByThemeSeq(themeSeq);
        assertEquals(categories, result);
    }

    @Test
    public void readOneCategoryByCategorySeqTest() {
        // Given
        Long categorySeq = 1L;
        Theme theme = new Theme(
                "themeName",
                1L, 1L);
        Category category = new Category(
                "categoryName",
                1L, 1L, theme);
        when(categoryRepository.findByCategorySeq(categorySeq))
                .thenReturn(Optional.of(category));

        // When
        CategoryDto result = categoryService.readOne(categorySeq);

        // Then
        verify(categoryRepository).findByCategorySeq(categorySeq);
        assertEquals(CategoryDto.toDto(category), result);
    }

    @Test
    public void testReadLastCategoryOrderByThemeSeq() {
        // Given
        Long themeSeq = 1L;
        Long lastOrder = 5L;
        when(categoryRepository.readLastCategoryOrderByThemeSeq(themeSeq))
                .thenReturn(lastOrder);

        // When
        Long result = categoryService.readLastCategoryOrderByThemeSeq(themeSeq);

        // Then
        verify(categoryRepository).readLastCategoryOrderByThemeSeq(themeSeq);
        assertEquals(lastOrder + 1, result);
    }
}
