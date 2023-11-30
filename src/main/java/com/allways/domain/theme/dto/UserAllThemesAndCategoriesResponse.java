package com.allways.domain.theme.dto;

import java.util.List;

import com.allways.domain.category.dto.CategoryDto;

import com.allways.domain.theme.entity.Theme;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAllThemesAndCategoriesResponse {
    private Long themeSeq;
    private String themeName;
    private Long themeOrder;
    private List<CategoryDto> categories;

    public UserAllThemesAndCategoriesResponse(Theme theme, List<CategoryDto> category) {
        this.themeSeq = theme.getThemeSeq();
        this.themeName = theme.getThemeName();
        this.themeOrder = theme.getThemeOrder();
        this.categories = category;
    }

}