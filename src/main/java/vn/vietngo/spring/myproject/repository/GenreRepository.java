package vn.vietngo.spring.myproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.vietngo.spring.myproject.entity.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
}
