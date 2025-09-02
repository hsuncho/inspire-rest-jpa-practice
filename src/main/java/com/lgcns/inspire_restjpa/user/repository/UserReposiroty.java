package com.lgcns.inspire_restjpa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lgcns.inspire_restjpa.user.domain.entity.UserEntity;

@Repository
public interface UserReposiroty extends JpaRepository<UserEntity, String>{
    
}
