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
public class TemplateQueryController {
    private final TemplateCommandService templateCommandService;

    // 템플릿(서식) 목록 불러오기
    @GetMapping("/api/template")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@RequestHeader(value = "userSeq") Long userSeq) {
        return Response.success(templateCommandService.readAll(userSeq));
    }

    // 템플릿(서식) 상세 보기
    @GetMapping("/api/template/{templateSeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOne(@PathVariable Long templateSeq) {
        return Response.success(templateCommandService.readOne(templateSeq));
    }
}
