package com.allways.domain.reply.service;

import com.allways.domain.reply.dto.ReplyDto;
import com.allways.domain.reply.dto.ReplyReadRequest;
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

    public List<ReplyDto> readReplies(ReplyReadRequest req) {
        //Reply > ReplyDto 로 변환
        return ReplyDto.toDtoList(
                replyRepository.findAllRepliesByPostSeq(req.getPostSeq()
        ));
    }
}
