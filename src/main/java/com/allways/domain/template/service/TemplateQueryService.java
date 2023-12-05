package com.allways.domain.template.service;

import com.allways.domain.template.entity.Template;
import com.allways.domain.template.dto.*;
import com.allways.domain.template.exception.TemplateNotFoundException;
import com.allways.domain.template.repository.TemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class TemplateQueryService {
    private final TemplateRepository templateRepository;

    // 선택된 templateSeq에 해당하는 template 정보 읽기
    public TemplateResponse readOne(Long templateSeq) {
        // 찾고자 하는 template가 없을 경우 TemplateNotFoundException 발생
        Template template = templateRepository.findByTemplateSeq(templateSeq).orElseThrow(TemplateNotFoundException::new);

        return TemplateResponse.toDto(template);
    }

    public TemplateResponse[] readAll(Long userSeq) {
        List<Template> allTemplates = templateRepository.findAllByUserSeq(userSeq).orElseThrow(TemplateNotFoundException::new);

        TemplateResponse[] templateResponses = allTemplates.stream()
                .map(TemplateResponse::toDto)
                .toArray(TemplateResponse[]::new);

        return templateResponses;
    }
}