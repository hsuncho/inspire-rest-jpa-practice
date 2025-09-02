package com.lgcns.inspire_restjpa.user.domain.dto;

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
public class UserRequestDTO {
    private String email, passwd, name;

    // factory method pattern
    // dto -> entity
    public UserEntity toEntity() {
        return UserEntity.builder()
                    .email(this.email)
                    .passwd(this.passwd)
                    .name(this.name)
                    .build();
    }
}
