package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTenSach(String ten);

    List<Book> findByTenSachContainingOrAuthor_HoVaTenContaining(String ten, String hoVaTen);

}


