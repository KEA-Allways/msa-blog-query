package com.allways.domain.theme.controller;

import com.allways.common.response.Response;
import com.allways.domain.theme.dto.UserAllThemesAndCategoriesResponse;
import com.allways.domain.theme.entity.Theme;
import com.allways.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ThemeQueryController {

    public final ThemeService themeService;

    //특정 유저의 테마 목록 조회
    @GetMapping("/api/theme/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readThemes (@PathVariable Long userSeq){
        List<Theme> theme = themeService.readThemes(userSeq);
        return Response.success(theme);
    }

    //특정 유저의 테마 및 카테고리 목록 조회
    @GetMapping("/api/theme/list/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readAllThemesAndCategories(@PathVariable Long userSeq){
        List<UserAllThemesAndCategoriesResponse> themesAndCategoriesResponse = themeService.readAllThemesAndCategories(userSeq);
        return Response.success(themesAndCategoriesResponse);
    }

}
