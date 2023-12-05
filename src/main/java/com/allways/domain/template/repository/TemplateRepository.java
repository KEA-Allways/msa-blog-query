package com.allways.domain.template.repository;

import com.allways.domain.template.entity.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    Optional<List<Template>> findAllByUserSeq(Long userSeq);

    Optional<Template> findByTemplateSeq(Long templateSeq);
}