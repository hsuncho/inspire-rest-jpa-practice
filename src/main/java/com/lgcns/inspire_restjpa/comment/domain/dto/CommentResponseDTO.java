package com.lgcns.inspire_restjpa.comment.domain.dto;

import com.lgcns.inspire_restjpa.comment.domain.entity.CommentEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentResponseDTO {
    private Integer commentId;
    private String comment;
    private Integer blogId;

    public static CommentResponseDTO fromEntity(CommentEntity comment) {
        return CommentResponseDTO.builder()
                            .commentId(comment.getCommentId())
                            .comment(comment.getComment())
                            .blogId(comment.getBlog().getBlogId())
                            .build();
    }
}
