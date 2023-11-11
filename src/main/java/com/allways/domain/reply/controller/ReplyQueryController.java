package com.allways.domain.reply.controller;

import com.allways.common.response.Response;
import com.allways.domain.reply.dto.ReplyReadRequest;
import com.allways.domain.reply.service.ReplyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ReplyQueryController {

    private final ReplyQueryService replyQueryService;
    @GetMapping("/api/posts/{postSeq}/replys")
    @ResponseStatus(HttpStatus.OK)
    public Response readAll(@Valid ReplyReadRequest req) {
        return Response.success(replyQueryService.readAll(req));
    }
}
