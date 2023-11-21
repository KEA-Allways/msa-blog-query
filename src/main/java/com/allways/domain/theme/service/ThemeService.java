package com.allways.domain.theme.service;

import com.allways.domain.theme.entity.Theme;
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

    @Transactional
    public Long readLastThemeOrderByUserSeq(Long userSeq){
        //테마 생성 시 가장 높은 themeOrder를 조회한 후 + 1
        Long lastOrder = themeRepository.readLastThemeOrderByUserSeq(userSeq);
        lastOrder += 1;
        return lastOrder;
    }
}
