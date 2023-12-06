package com.allways.domain.theme.repository;

import com.allways.domain.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("select t from Theme t where t.userSeq = :userSeq order by t.themeOrder ASC")
    List<Theme> findThemeByUserSeqOrderByThemeOrder(@Param("userSeq") Long userSeq);

    @Query("select COALESCE(max(themeOrder), 0) from Theme t where t.userSeq = :userSeq")
    Long readLastThemeOrderByUserSeq(@Param("userSeq") Long userSeq);

    Theme findThemeByThemeSeq(Long themeSeq);
}
