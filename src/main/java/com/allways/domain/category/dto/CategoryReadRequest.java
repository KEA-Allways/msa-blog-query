package com.allways.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReadRequest {
    private Long categorySeq;
    private String categoryName;
    private Long categoryOrder;
    private Long themeSeq;
}
