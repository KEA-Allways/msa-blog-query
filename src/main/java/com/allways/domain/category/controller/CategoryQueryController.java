package com.allways.domain.category.controller;

import com.allways.common.response.Response;
import com.allways.domain.category.dto.CategoryReadRequest;
import com.allways.domain.category.service.CategoryQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryQueryController {

    private final CategoryQueryService categoryQueryService;

    @GetMapping("/api/themes/{themeSeq}/categories")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@Valid CategoryReadRequest req){
        System.out.println("controller req :" + req);
        return Response.success(categoryQueryService.readAll(req));
    }


    @GetMapping("/api/categories/{categorySeq}")
    @ResponseStatus(HttpStatus.OK)
    public Response readOne(@PathVariable Long categorySeq){
        return Response.success(categoryQueryService.readOne(categorySeq));
    }


}
