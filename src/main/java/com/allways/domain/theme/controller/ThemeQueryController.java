package com.allways.domain.theme.controller;

import com.allways.domain.theme.domain.Theme;
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

    @GetMapping("/api/themes/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public List<Theme> readThemes(@PathVariable Long userSeq){
        return themeService.readThemes(userSeq);
    }

}
