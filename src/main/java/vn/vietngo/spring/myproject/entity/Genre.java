package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.springframework.data.annotation.Id;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Entity
@Table(name="genres")
public class Genre {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id ;

    @Column(name="tentheloai")
    private String tentheLoai;

    @ManyToMany(mappedBy = "genres")
    private List<Book> books;

    public Genre() {
    }

    public Genre(List<Book> books, String tentheLoai) {
        this.books = books;
        this.tentheLoai = tentheLoai;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long maTheLoai) {
        this.id = id;
    }

    public String getTentheLoai() {
        return tentheLoai;
    }

    public void setTentheLoai(String tentheLoai) {
        this.tentheLoai = tentheLoai;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", tentheLoai='" + tentheLoai + '\'' +
                ", books=" + books +
                '}';
    }

}
