package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Review;
import vn.vietngo.spring.myproject.service.AccountService;
import vn.vietngo.spring.myproject.service.BookService;
import vn.vietngo.spring.myproject.service.ReviewService;
import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {
    private ReviewService reviewService;
    private BookService bookService;
    private AccountService accountService;

    @Autowired
    public ReviewController(ReviewService reviewService, BookService bookService, AccountService accountService) {
        this.reviewService = reviewService;
        this.accountService = accountService;
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public String addComment(@RequestParam(value = "content") String content,@RequestParam(value = "rate") Integer rate, @RequestParam(value = "time") LocalDateTime localDateTime, @RequestParam(value = "id")Long id, Principal principal) {
        Book book = bookService.getBookById(id);
        String time = localDateTime.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        Review review = reviewService.getReviewByAccountAndBook(account, book);
        review = (review == null) ? new Review(account, book) : review;
        review.setContent(content);
        review.setTime(time);
        review.setRate(rate);
        reviewService.addReview(review);
        //tinh toan so luong danh gia va diem danh gia cua sach
        List<Review> reviewList = reviewService.getReviewsByBook(id);
        float tongDanhGia = 0;
        for(Review review1: reviewList) tongDanhGia += review1.getRate();
        book.setSoDanhGia(reviewList.size());
        book.setDiemDanhGia(tongDanhGia/(reviewList.size()));
        bookService.updateBook(book);
        return "redirect:/book?id=" + id;
    }
}
