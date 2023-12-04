package com.allways.domain.category.controller;

import com.allways.domain.category.dto.CategoryDto;
import com.allways.domain.category.entity.Category;
import com.allways.domain.category.service.CategoryQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)

public class CategoryQueryControllerTest {
    @Mock private CategoryQueryService categoryQueryService;
    @InjectMocks private CategoryQueryController categoryQueryController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        // MockMvc를 설정하여 컨트롤러를 테스트할 수 있는 환경을 구성합니다.
        mockMvc = MockMvcBuilders.standaloneSetup(categoryQueryController).build();
    }

    @Test
    void readAllTest() throws Exception {
        // Given
        Long themeSeq = 123L;
        List<Category> CategoryList = new ArrayList<>();
        CategoryList.add(new Category(
                "categoryName",
                1L,
                123L));

        // When
        when(categoryQueryService.readAll(themeSeq))
                .thenReturn(CategoryList);

        // Then
        mockMvc.perform(get("/api/theme/{themeSeq}/category", themeSeq)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(categoryQueryService).readAll(themeSeq);
    }

    @Test
    void readOneTest() throws Exception {
        // Given
        Long categorySeq = 1L;
        CategoryDto categoryDto = new CategoryDto(
                1L,
                "categoryName",
                1L,
                "themeName");

        // When
        when(categoryQueryService.readOne(categorySeq))
                .thenReturn(categoryDto);

        // Then
        mockMvc.perform(get("/api/category/{categorySeq}", categorySeq)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(categoryQueryService).readOne(categorySeq);
    }
}
