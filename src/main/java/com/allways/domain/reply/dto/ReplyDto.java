package com.allways.domain.reply.dto;

import com.allways.common.helper.NestedConvertHelper;
import com.allways.domain.reply.domain.Reply;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private Long replySeq;
    private String replyContent;
    //private MemberDto member; //추후에 주석 해제 필요
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdAt;
    //private List<ReplyDto> children;  //댓글의 댓글 기능 주석 처리

    public static List<ReplyDto> toDtoList(List<Reply> replies) {
        NestedConvertHelper helper = NestedConvertHelper.newInstance(
                replies,
                c -> new ReplyDto(
                        c.getReplySeq()
                        , c.isDeleted() ? null : c.getReplyContent()
                        //, c.isDeleted() ? null : MemberDto.toDto(c.getMember())   //추후에 주석 해제 필요
                        , c.getCreatedAt()
                        //, new ArrayList<>() //댓글의 댓글 기능 주석 처리
                ),
                c -> c.getParent(),
                c -> c.getReplySeq(),
                null  //d -> d.getChildren() //댓글의 댓글 기능 주석 처리
        );
        return helper.convert();
    }

}
