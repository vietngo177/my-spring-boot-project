package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;
import org.springframework.context.annotation.Lazy;

import java.sql.Blob;
import java.time.Year;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="masach")
    private Long id;

    @Column(name="tensach")
    private String tenSach;

    @Column(name="namxuatban")
    private Year namXuatBan;

    @Column(name="diemdanhgia")
    private Float diemDanhGia;

    @Lob
    @Column(name="hinhanh")
    private Blob hinhAnh;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name="books_genres",
            joinColumns = @JoinColumn(name="book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    public Book() {}

    public Book(String tenSach, Year namXuatBan, Float diemDanhGia, Blob hinhAnh, Author author, List<Genre> genres) {
        this.tenSach = tenSach;
        this.namXuatBan = namXuatBan;
        this.diemDanhGia = diemDanhGia;
        this.hinhAnh = hinhAnh;
        this.author = author;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public Year getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(Year namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public Float getDiemDanhGia() {
        return diemDanhGia;
    }

    public void setDiemDanhGia(Float diemDanhGia) {
        this.diemDanhGia = diemDanhGia;
    }

    public Blob getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(Blob hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", tenSach='" + tenSach + '\'' +
                ", namXuatBan=" + namXuatBan +
                ", diemDanhGia=" + diemDanhGia +
                ", hinhAnh=" + hinhAnh +
                ", author=" + author +
                ", genres=" + genres +
                '}';
    }
}
