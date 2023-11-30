package com.allways.domain.theme.controller;

import com.allways.domain.theme.service.ThemeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ThemeFeignController {
    private final ThemeService themeService;

    @GetMapping("/api/theme/feign/readThemeOrder/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Long readLastThemeOrderByUserSeq(@PathVariable Long userSeq){
        return themeService.readLastThemeOrderByUserSeq(userSeq);
    }
}
