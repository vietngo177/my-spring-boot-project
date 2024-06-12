package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Review;

import java.util.List;

public interface ReviewService {
    void addReview(Review review);

    List<Review> getReviewsByBook(Long id);

    List<Review> getAllReview();

    Review getReviewByAccountAndBook(Account account, Book book);
}
