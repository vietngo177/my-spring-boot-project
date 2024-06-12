package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Comment;
import vn.vietngo.spring.myproject.service.AccountService;
import vn.vietngo.spring.myproject.service.BookService;
import vn.vietngo.spring.myproject.service.CommentService;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Controller
@RequestMapping("/comment")
public class CommentController {
    private CommentService commentService;
    private BookService bookService;
    private AccountService accountService;

    @Autowired
    public CommentController(CommentService commentService, BookService bookService, AccountService accountService) {
        this.commentService = commentService;
        this.accountService = accountService;
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public String addComment(@RequestParam(value = "content") String content, @RequestParam(value = "time") LocalDateTime localDateTime, @RequestParam(value = "id")Long id, Principal principal) {
        Book book = bookService.getBookById(id);
        String time = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        Comment comment = new Comment(time, content, account, book);
        commentService.addComment(comment);
        return "redirect:/book?id=" + id;
    }
}
