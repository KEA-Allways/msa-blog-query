package com.allways.domain.category.controller;

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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CategoryFeignControllerTest {
    @Mock private CategoryQueryService categoryQueryService;
    @InjectMocks private CategoryFeignController categoryFeignController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        // MockMvc를 설정하여 컨트롤러를 테스트할 수 있는 환경을 구성합니다.
        mockMvc = MockMvcBuilders.standaloneSetup(categoryFeignController).build();
    }

    @Test
    void readLastCategoryOrderByThemeSeq_ReturnsCategoryOrder() throws Exception {
        // Given
        Long themeSeq = 123L;

        // When
        when(categoryQueryService.readLastCategoryOrderByThemeSeq(anyLong()))
                .thenReturn(themeSeq);

        // Then
        mockMvc.perform(get("/api/category/feign/readCategoryOrder/{themeSeq}", themeSeq)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(categoryQueryService).readLastCategoryOrderByThemeSeq(themeSeq);
    }
}
