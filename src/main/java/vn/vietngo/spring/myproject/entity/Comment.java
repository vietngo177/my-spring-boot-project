package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

@Entity
@Table(name="comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="thoigian")
    private String time;


    @Column(name="content")
    @NotBlank
    private String content;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="book_id")
    private Book book;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="parent_comment_id")
    private Comment parentComment;

    public Comment() {}

    public Comment(String time, String content, Account account, Book book, Comment parentComment) {
        this.time = time;
        this.content = content;
        this.account = account;
        this.book = book;
        this.parentComment = parentComment;
    }

    public Comment(String time, String content, Account account, Book book) {
        this.time = time;
        this.content = content;
        this.account = account;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Comment getComment() {
        return parentComment;
    }

    public void setComment(Comment comment) {
        this.parentComment = comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", localDateTime=" + time +
                ", content='" + content + '\'' +
                ", account=" + account +
                ", book=" + book +
                ", comment=" + parentComment +
                '}';
    }
}
