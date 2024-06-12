package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;
import java.time.Year;
import java.util.ArrayList;

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

    @Column(name="sodanhgia")
    private Integer soDanhGia;

    @Column(name="hinhanh")
    private String hinhAnh;

    @Column(name="tomtat")
    private String tomTat;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="genre_id")
    private Genre genre;

    public Book() {}

    public Book(String tenSach, Year namXuatBan, Float diemDanhGia, String hinhAnh, Author author, Genre genres) {
        this.tenSach = tenSach;
        this.namXuatBan = namXuatBan;
        this.diemDanhGia = diemDanhGia;
        this.hinhAnh = hinhAnh;
        this.author = author;
        this.genre = genres;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
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

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Integer getSoDanhGia() {
        return soDanhGia;
    }

    public void setSoDanhGia(Integer soDanhGia) {
        this.soDanhGia = soDanhGia;
    }

    @Override
    public String toString() {
        return tenSach;
    }

    @Transient
    public String getHinhAnhPath() {
        if (id == null && hinhAnh == null) return null;
        return "/img/" + id + "/" + hinhAnh;
    }
}
