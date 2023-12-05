package com.allways.domain.category.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.allways.domain.category.entity.Category;
import com.allways.domain.theme.entity.Theme;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class CategoryDtoTest {

    @Test
    public void toDtoListTest() {
        // 테스트할 Category 객체들을 생성하고 리스트에 추가합니다
        List<Category> categories = new ArrayList<>();
        Category category1 = new Category(
                "categoryName1", 1L, 1L,
                new Theme("themeName1", 1L, 1L));
        Category category2 = new Category(
                "categoryName2", 2L, 2L,
                new Theme("themeName2", 2L, 2L));
        categories.add(category1);
        categories.add(category2);

        // CategoryDto.toDtoList() 메소드를 호출하여 DTO 리스트를 가져옵니다
        List<CategoryDto> dtoList = CategoryDto.toDtoList(categories);

        // DTO 리스트의 크기와 기대값을 비교합니다
        assertEquals(categories.size(), dtoList.size());

        // 각 DTO가 올바르게 매핑되었는지 확인합니다
        assertEquals(category1.getCategorySeq(), dtoList.get(0).getCategorySeq());
        assertEquals(category1.getCategoryName(), dtoList.get(0).getCategoryName());
        assertEquals(category1.getCategoryOrder(), dtoList.get(0).getCategoryOrder());
        assertEquals(category1.getTheme().getThemeName(), dtoList.get(0).getThemeName());

        assertEquals(category2.getCategorySeq(), dtoList.get(1).getCategorySeq());
        assertEquals(category2.getCategoryName(), dtoList.get(1).getCategoryName());
        assertEquals(category2.getCategoryOrder(), dtoList.get(1).getCategoryOrder());
        assertEquals(category2.getTheme().getThemeName(), dtoList.get(1).getThemeName());
    }

    @Test
    public void toDtoTest() {
        // 테스트할 Category 객체를 생성합니다
        Category category = new Category(
                "categoryName", 1L, 1L,
                new Theme("themeName", 1L, 1L));

        // CategoryDto.toDto() 메소드를 호출하여 DTO를 가져옵니다
        CategoryDto dto = CategoryDto.toDto(category);

        // DTO 값과 기대값을 비교합니다
        assertEquals(category.getCategorySeq(), dto.getCategorySeq());
        assertEquals(category.getCategoryName(), dto.getCategoryName());
        assertEquals(category.getCategoryOrder(), dto.getCategoryOrder());
        assertEquals(category.getTheme().getThemeName(), dto.getThemeName());
    }
}
