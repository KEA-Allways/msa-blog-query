package com.allways.domain.reply.controller;

import com.allways.common.response.Response;
import com.allways.domain.reply.dto.ReplyReadRequest;
import com.allways.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class ReplyQueryController {

    private final ReplyService replyQueryService;
    @GetMapping("/api/posts/{postSeq}/replies")
    @ResponseStatus(HttpStatus.OK)
    public Response readReplies(@Valid ReplyReadRequest req) {
        return Response.success(replyQueryService.readReplies(req));
    }
}
