package com.allways.domain.template.controller;

import com.allways.common.response.Response;
import com.allways.domain.template.service.TemplateCommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TemplateCommandController {
    private final TemplateCommandService templateCommandService;

    // 템플릿(서식) 목록 불러오기
    @GetMapping("/api/templates/{userSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@PathVariable Long userSeq) {
        return Response.success(templateCommandService.readAll(userSeq));
    }

    // 템플릿(서식) 한개 내용물 불러오기
    @GetMapping("/api/templates/{userSeq}/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOne(@PathVariable Long userSeq, @PathVariable Long templateSeq) {
        return Response.success(templateCommandService.readOne(userSeq, templateSeq));
    }
}
