package com.allways.domain.category.service;

import com.allways.domain.category.domain.Category;
import com.allways.domain.category.dto.CategoryDto;
import com.allways.domain.category.dto.CategoryReadRequest;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.template.exception.TemplateNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryQueryService {
    public final CategoryRepository categoryRepository;

    public List<CategoryDto> readAll(CategoryReadRequest req) {
        return CategoryDto.toDtoList(
                categoryRepository.findAllCategoriesWithThemesByThemeSeq(req.getThemeSeq())
        );
    }

    public CategoryDto readOne(Long categorySeq){
        Category category = categoryRepository.findByCategorySeq(categorySeq).orElseThrow(TemplateNotFoundException::new);

        return CategoryDto.toDto(category);
    }

}
