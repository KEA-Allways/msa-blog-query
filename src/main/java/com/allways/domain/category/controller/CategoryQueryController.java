package com.allways.domain.category.controller;

import com.allways.common.response.Response;
import com.allways.domain.category.service.CategoryQueryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping("/api/theme/{themeSeq}/category")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@PathVariable Long themeSeq){
        return Response.success(categoryQueryService.readAll(themeSeq));
    }

    @GetMapping("/api/category/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOne(@PathVariable Long categorySeq){
        return Response.success(categoryQueryService.readOne(categorySeq));
    }
}
