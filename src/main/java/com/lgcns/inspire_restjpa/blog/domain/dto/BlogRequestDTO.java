package com.lgcns.inspire_restjpa.blog.domain.dto;

import com.lgcns.inspire_restjpa.blog.domain.entity.BlogEntity;
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
public class BlogRequestDTO {
    private String title, content;
    private String authorEmail; // FK

    public BlogEntity toEntity(UserEntity author) {
        return BlogEntity.builder()
                .title(this.title)
                .content(this.content)
                .author(author)
                .build();
    }
}
