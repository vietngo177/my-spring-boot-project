package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByBook_Id(Long id);

    Review findByAccountAndBook(Account account, Book book);
}
