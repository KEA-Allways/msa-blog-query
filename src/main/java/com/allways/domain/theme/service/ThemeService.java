package com.allways.domain.theme.service;

import com.allways.domain.category.dto.CategoryDto;
import com.allways.domain.category.repository.CategoryRepository;
import com.allways.domain.theme.dto.UserAllThemesAndCategoriesResponse;
import com.allways.domain.theme.entity.Theme;
import com.allways.domain.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ThemeService {

    public final ThemeRepository themeRepository;
    public final CategoryRepository categoryRepository;

    public List<Theme> readThemes (Long userSeq) {
        return themeRepository.findThemeByUserSeqOrderByThemeOrder(userSeq);
    }

    public String readTheme(Long themeSeq){
        return themeRepository.findThemeByThemeSeq(themeSeq).getThemeName();
    }

    public List<UserAllThemesAndCategoriesResponse> readAllThemesAndCategories(Long userSeq) {
        List<Theme> themeList = themeRepository.findThemeByUserSeqOrderByThemeOrder(userSeq);
        List<UserAllThemesAndCategoriesResponse> themeCategories = new ArrayList<>();
        for (Theme theme : themeList) {
            Long themeSeq = theme.getThemeSeq();
            List<CategoryDto> categoryLists = CategoryDto.toDtoList(categoryRepository.findAllCategoriesWithThemesByThemeSeq(themeSeq));
            themeCategories.add(new UserAllThemesAndCategoriesResponse(theme, categoryLists));
        }
        return themeCategories;
    }

    @Transactional
    public Long readLastThemeOrderByUserSeq(Long userSeq){
        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        Long lastOrder = themeRepository.readLastThemeOrderByUserSeq(userSeq);
        lastOrder += 1;
        return lastOrder;
    }
}