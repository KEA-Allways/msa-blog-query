package com.allways.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryReadRequest {
    @NotNull
    private Long categorySeq;
    @NotBlank
    private String categoryName;
    @NotNull
    private Long categoryOrder;
    @NotNull
    private Long themeSeq;
}