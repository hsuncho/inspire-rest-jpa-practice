package com.lgcns.inspire_restjpa;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import com.lgcns.inspire_restjpa.blog.domain.dto.BlogRequestDTO;
import com.lgcns.inspire_restjpa.blog.domain.entity.BlogEntity;
import com.lgcns.inspire_restjpa.blog.repository.BlogRepository;
import com.lgcns.inspire_restjpa.comment.domain.dto.CommentRequestDTO;
import com.lgcns.inspire_restjpa.comment.domain.entity.CommentEntity;
import com.lgcns.inspire_restjpa.comment.repository.CommentRepository;
import com.lgcns.inspire_restjpa.user.domain.dto.UserRequestDTO;
import com.lgcns.inspire_restjpa.user.domain.dto.UserResponseDTO;
import com.lgcns.inspire_restjpa.user.domain.entity.UserEntity;
import com.lgcns.inspire_restjpa.user.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit
public class UserBlogCommentTest {
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private CommentRepository commentRepository;

    /*
    시나리오
    로그인 한 사용자가 있고
    로그인 한 사용자가 Blog 작성(author)
    Comment 작성 시(blog)
    Blog 조회 시 -> User and Comment 연관관계를 검증
    댓글 삭제 시 ->
    */

    @Test
    public void user_blog_comment() {
        // 1. 회원가입 후 로그인
        UserRequestDTO request = UserRequestDTO.builder()
                                        .email("abc@email.com")
                                        .passwd("1234")
                                        .name("김영희")
                                        .build();
        userRepository.save(request.toEntity());
        
        UserEntity findUser = userRepository.findByEmailAndPasswd(request.getEmail(), request.getPasswd());
        UserResponseDTO response = UserResponseDTO.fromEntity(findUser);

        System.out.println(">>> 회원가입 후 로그인 성공");
        System.out.println(">>> " + response);

        // 2. 사용자가 블로그 작성
        // ctrl params
        BlogRequestDTO blogRequest = BlogRequestDTO.builder()
                                            .title("jpa")
                                            .content("error")
                                            .authorEmail("abc@email.com")
                                            .build();

        // service
        Optional<UserEntity> user = userRepository.findById(blogRequest.getAuthorEmail());
        BlogEntity blog = blogRepository.save(
            BlogEntity.builder()
                    .title(blogRequest.getTitle())
                    .content(blogRequest.getContent())
                    .author(user.get())
                    .build()
        );

        // 3. 다른 사용자가 댓글  작성
        // ctrl params
        CommentRequestDTO commentRequest = CommentRequestDTO.builder()
                                                        .comment("비밀 댓글입니다.")
                                                        .blogId(1)
                                                        .build();
        // service
        BlogEntity foundBlog = blogRepository.findById(commentRequest.getBlogId())
                                                        .orElseThrow(() -> new RuntimeException("블로그 존재 x"));
        CommentEntity comment = commentRepository.save(
            CommentEntity.builder()
                    .comment(commentRequest.getComment())
                    .blog(foundBlog)
                    .build()
        );
    }

}
