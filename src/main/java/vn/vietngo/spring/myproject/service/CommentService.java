package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Comment;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    List<Comment> getCommentsByBook(Long id);
}
