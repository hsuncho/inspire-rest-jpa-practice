package com.lgcns.inspire_restjpa.comment.domain.dto;

import com.lgcns.inspire_restjpa.blog.domain.entity.BlogEntity;
import com.lgcns.inspire_restjpa.comment.domain.entity.CommentEntity;
import com.lgcns.inspire_restjpa.user.domain.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommentRequestDTO {
    private String comment;
    private Integer blogId;

    public CommentEntity toEntity(BlogEntity blog) {
        return CommentEntity.builder()
                .comment(this.comment)
                .blog(blog)
                .build();
    }
}
