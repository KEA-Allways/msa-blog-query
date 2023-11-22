package com.allways.domain.reply.controller;

import com.allways.common.response.Response;
import com.allways.domain.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReplyQueryController {

    private final ReplyService replyQueryService;
    @GetMapping("/api/post/{postSeq}/reply")
    @ResponseStatus(HttpStatus.OK)
    public Response readReply(@PathVariable Long postSeq) {
        return Response.success(replyQueryService.readReply(postSeq));
    }
}
