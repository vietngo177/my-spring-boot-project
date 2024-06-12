package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Review;
import vn.vietngo.spring.myproject.repository.ReviewRepository;

import java.util.List;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    public ReviewServiceImpl() {}

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void addReview(Review review) {
        reviewRepository.saveAndFlush(review);
    }

    @Override
    public List<Review> getReviewsByBook(Long id) {
        return reviewRepository.findByBook_Id(id);
    }

    @Override
    public Review getReviewByAccountAndBook(Account account, Book book) {
        return reviewRepository.findByAccountAndBook(account, book);
    }

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }
}
