package com.lgcns.inspire_restjpa.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lgcns.inspire_restjpa.comment.domain.entity.CommentEntity;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
    
}
