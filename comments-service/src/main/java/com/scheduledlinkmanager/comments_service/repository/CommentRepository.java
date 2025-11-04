package com.scheduledlinkmanager.comments_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scheduledlinkmanager.comments_service.model.Comment;
import java.util.UUID;


public interface CommentRepository  extends JpaRepository<Comment, Long> {
        List<Comment> findByCodeId(UUID codeId);
}
