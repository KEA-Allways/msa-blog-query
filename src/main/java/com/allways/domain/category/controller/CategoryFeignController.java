package com.allways.domain.category.controller;

import com.allways.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryFeignController {
    private final CategoryQueryService categoryQueryService;

    @GetMapping("/api/category/feign/readCategoryOrder/{themeSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Long readLastCategoryOrderByThemeSeq(@PathVariable Long themeSeq){
        return categoryQueryService.readLastCategoryOrderByThemeSeq(themeSeq);
    }
}
