package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;

@Entity
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="rate")
    private Integer rate;

    @Column(name="content")
    private String content;

    @Column(name="thoigian")
    private String time;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="account_id")
    private Account account;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="book_id")
    private Book book;

    public Review() {}

    public Review(Account account, Book book) {
        this.account = account;
        this.book = book;
    }

    public Review(Integer rate, String content, String time, Account account, Book book) {
        this.rate = rate;
        this.content = content;
        this.account = account;
        this.book = book;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", rate=" + rate +
                ", content='" + content + '\'' +
                ", account=" + account +
                ", book=" + book +
                '}';
    }
}
