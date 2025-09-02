package com.lgcns.inspire_restjpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.lgcns.inspire_restjpa.user.domain.dto.UserRequestDTO;
import com.lgcns.inspire_restjpa.user.domain.dto.UserResponseDTO;
import com.lgcns.inspire_restjpa.user.domain.entity.UserEntity;
import com.lgcns.inspire_restjpa.user.repository.UserReposiroty;

@SpringBootTest
public class UserTest {
    @Autowired
    private UserReposiroty userReposiroty;
    
    @Test
    @Transactional
    @Rollback(false)
    public void insertUser() {
        // given
        UserRequestDTO request = UserRequestDTO.builder()
                                    .email("abc1234@email.com")
                                    .passwd("1234")
                                    .name("김철수")
                                    .build();

        // when
        UserEntity entity = userReposiroty.save(request.toEntity());
        UserResponseDTO response = UserResponseDTO.fromEntity(entity);
        
        // then
        System.out.println(">>> entity " + entity);
        System.out.println(">>> response " + response);
    }
}
