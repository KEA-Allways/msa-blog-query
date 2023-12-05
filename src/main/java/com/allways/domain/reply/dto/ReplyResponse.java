package com.allways.domain.reply.dto;

import com.allways.common.Constants;
import com.allways.common.helper.NestedConvertHelper;
import com.allways.domain.post.entity.Post;
import com.allways.domain.reply.entity.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ReplyResponse {
    private Long replySeq;
    private String replyContent;
    private String userId;
    private String nickname;
    private String profileImg;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;

    public ReplyResponse(Reply reply, String userId, String nickName ) {
        this.replySeq = reply.getReplySeq();
        this.replyContent = reply.getReplyContent();
        this.createdAt = reply.getCreatedAt();
        this.userId = userId;
        this.nickname = nickName;
    }
}