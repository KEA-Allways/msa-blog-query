package com.allways.domain.theme.repository;

import com.allways.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Query("select t from Theme t where t.userSeq = :userSeq order by t.themeOrder ASC")
    List<Theme> findThemeByUserSeqOrderByThemeOrder(@Param("userSeq") Long userSeq);
}
