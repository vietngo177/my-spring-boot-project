package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Comment;
import vn.vietngo.spring.myproject.repository.CommentRepository;

import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;

    public CommentServiceImpl() {}

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public void addComment(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentsByBook(Long id) {
        return commentRepository.findByBook_Id(id);
    }
}
