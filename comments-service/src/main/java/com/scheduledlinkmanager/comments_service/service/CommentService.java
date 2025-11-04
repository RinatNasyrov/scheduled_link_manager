package com.scheduledlinkmanager.comments_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import com.scheduledlinkmanager.comments_service.dto.CommentDTO;
import com.scheduledlinkmanager.comments_service.model.Comment;
import com.scheduledlinkmanager.comments_service.repository.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(CommentDTO commentDTO) {
        if (commentDTO == null)
            return;

        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setUserId(commentDTO.getUserId());
        comment.setCodeId(commentDTO.getCodeId());
        comment.setContent(commentDTO.getContent());

        commentRepository.save(comment);
    }

    public void deleteComment(Long id) {
        if (id != null)
            commentRepository.deleteById(id);
    }

    public List<CommentDTO> getComments(UUID codeId) {
        if (codeId == null)
            return null;
    
        List<Comment> comments = commentRepository.findByCodeId(codeId);
        List<CommentDTO> commentDTOs = new ArrayList<>();
        Comment comment = null;
        CommentDTO commentDTO = null;
        for (int i = 0; i < comments.size(); i++) {
            comment = comments.get(i);
            commentDTO = new CommentDTO();

            commentDTO.setId(comment.getId());
            commentDTO.setUserId(comment.getUserId());
            commentDTO.setCodeId(comment.getCodeId());
            commentDTO.setContent(comment.getContent());

            commentDTOs.add(commentDTO);
        }

        return commentDTOs;
    }
}
