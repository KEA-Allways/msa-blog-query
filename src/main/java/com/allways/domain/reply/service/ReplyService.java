package com.allways.domain.reply.service;

import com.allways.common.feign.user.UserFeignClient;
import com.allways.common.feign.user.UserFeignService;
import com.allways.domain.reply.dto.ReplyResponse;
import com.allways.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    public final ReplyRepository replyRepository;
    private final UserFeignService userFeignService;

    public List<ReplyResponse> readReply(Long postSeq) {



        //Reply > ReplyDto 로 변환
        return ReplyResponse.toDtoList(
                replyRepository.findAllRepliesByPostSeq(postSeq));
    }
}
