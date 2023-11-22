package com.allways.domain.category.dto;

import com.allways.common.helper.NestedConvertHelper;
import com.allways.domain.category.domain.Category;
import com.allways.domain.template.domain.Template;
import com.allways.domain.template.dto.TemplateDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long categorySeq;
    private String categoryName;
    private Long categoryOrder;
    private String themeName;

    public static List<CategoryDto> toDtoList(List<Category> categories){
        List<CategoryDto> dtoList = new ArrayList<>();

        for(Category category : categories){
            CategoryDto dto = new CategoryDto();
            dto.setCategorySeq(category.getCategorySeq());
            dto.setCategoryName(category.getCategoryName());
            dto.setCategoryOrder(category.getCategoryOrder());
            dto.setThemeName(category.getTheme().getThemeName());

            dtoList.add(dto);
        }

        return dtoList;
    }

    public static CategoryDto toDto(Category category){
        return new CategoryDto(
                category.getCategorySeq(),
                category.getCategoryName(),
                category.getCategoryOrder(),
                category.getTheme().getThemeName()
        );
    }
}
