package vn.vietngo.spring.myproject.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name="authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="hovaten")
    private String hoVaTen;

    @Column(name="ngaysinh")
    private Date ngaySinh;

    @Column(name="tieusu")
    private String tieusu;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {}

    public Author(String hoVaTen, Date ngaySinh, String tieusu, List<Book> books) {
        this.books = books;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.tieusu = tieusu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getTieusu() {
        return tieusu;
    }

    public void setTieusu(String tieusu) {
        this.tieusu = tieusu;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", tieusu='" + tieusu + '\'' +
                ", books=" + books +
                '}';
    }
}
