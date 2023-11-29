package com.allways.domain.reply.service;

import com.allways.common.feign.filedb.FileFeignReplyResponse;
import com.allways.common.feign.filedb.FileFeignService;
import com.allways.common.feign.user.UserFeignService;
import com.allways.common.feign.user.dto.UserByReplyFeignRequest;
import com.allways.common.feign.user.dto.UserByReplyResponse;
import com.allways.domain.reply.dto.ReplyResponse;
import com.allways.domain.reply.entity.Reply;
import com.allways.domain.reply.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyService {

    public final ReplyRepository replyRepository;
    private final UserFeignService userFeignService;
    private final FileFeignService fileFeignService;

    public List<ReplyResponse> readReply(Long postSeq) {
        List<Reply> replies = replyRepository.findAllRepliesByPostSeq(postSeq);

        List<UserByReplyFeignRequest> userFeignList = new ArrayList<>();

        for (Reply reply : replies){
            UserByReplyFeignRequest userByReplyFeignRequest = new UserByReplyFeignRequest(reply.getReplySeq(), reply.getUserSeq());
            userFeignList.add(userByReplyFeignRequest);
        }

        List<UserByReplyResponse> userByReplyResponseList = userFeignService.queryUsersByReply(userFeignList);
        List<FileFeignReplyResponse> fileFeignReplyResponseList = fileFeignService.queryImageUrlByReply(userFeignList);

        List<ReplyResponse> replyResponses = new ArrayList<>();

        for (Reply reply : replies){
            for (UserByReplyResponse userByReplyResponse : userByReplyResponseList){
                if (reply.getReplySeq() == userByReplyResponse.getReplySeq()){
                    replyResponses.add(new ReplyResponse(reply, userByReplyResponse.getUserId(), userByReplyResponse.getNickname()));
                }
            }

            for (FileFeignReplyResponse fileFeignReplyResponse : fileFeignReplyResponseList){
                if (reply.getReplySeq() == fileFeignReplyResponse.getReplySeq()){
                    for (ReplyResponse replyResponse : replyResponses) {
                        if (replyResponse.getReplySeq() == reply.getReplySeq()) {
                            replyResponse.setProfileImg(fileFeignReplyResponse.getProfileImg());
                        }
                    }
                }
            }
        }

        return replyResponses;
    }
}
