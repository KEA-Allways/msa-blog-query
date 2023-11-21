package com.allways.domain.template.service;

import com.allways.domain.template.domain.Template;
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
public class TemplateCommandService {
    private final TemplateRepository templateRepository;

    // 선택된 templateSeq에 해당하는 template 정보 읽기
    public TemplateResponse readOne(Long templateSeq) {
        // 찾고자 하는 template가 없을 경우 TemplateNotFoundException 발생
        Template template = templateRepository.findByTemplateSeq(templateSeq).orElseThrow(TemplateNotFoundException::new);

        return TemplateResponse.toDto(template);
    }

    // 원하는 유저의 모든 template들을 읽어오는 readAll 함수
    // ALL을 땡겨오는거라 날짜와 userSeq까지 가져올거 같긴 한데
    // gpt 도움받은거라 확실하지는 않음 확인 필요
    public TemplateResponse[] readAll(Long userSeq) {
        // 찾고자 하는 user가 만든 Template가 없을 경우 TemplateNotFoundException 발생
        // ********* 근데 어떠한 유저가 template가 없는게 에러 상황은 아닌데 이걸 Exception 에러로 처리하는게 맞나? ******************
        List<Template> allTemplates = templateRepository.findAllByUserSeq(userSeq).orElseThrow(TemplateNotFoundException::new);

        TemplateResponse[] templateResponses = allTemplates.stream()
                .map(TemplateResponse::toDto)
                .toArray(TemplateResponse[]::new);

        return templateResponses;
    }
}
