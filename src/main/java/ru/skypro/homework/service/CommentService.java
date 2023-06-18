package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.CommentDTO;
import ru.skypro.homework.dto.CreateCommentDTO;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.ResponseWrapperComment;
import ru.skypro.homework.entity.Comment;

public interface CommentService {
    ResponseWrapperComment getAllComments(int id);

    CommentDTO addComment(int id, CreateCommentDTO comment, Authentication authentication);

    void deleteComment(int adId, int commentId);

    CommentDTO updateComment(int adId, int commentId, Comment comment);
}
