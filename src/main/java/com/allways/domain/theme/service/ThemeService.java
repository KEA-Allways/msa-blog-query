package com.allways.domain.theme.service;

import com.allways.domain.theme.domain.Theme;
import com.allways.domain.theme.repository.ThemeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ThemeService {

    public final ThemeRepository themeRepository;

    public List<Theme> readThemes (Long userSeq){
        return themeRepository.findThemeByUserSeqOrderByThemeOrder(userSeq);
    }
}
