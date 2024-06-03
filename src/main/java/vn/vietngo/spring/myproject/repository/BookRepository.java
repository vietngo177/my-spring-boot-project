package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
//    @Query("SELECT s FROM Book s WHERE s.tenSach LIKE '%?1%'")
    List<Book> findByTenSach(String ten);
}


