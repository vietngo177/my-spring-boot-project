package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

public interface BookService {

    Book updateBook(Book book);

    List<Book> getBookByTenSach(String ten);

    List<Book> getAllBook();

    List<Book> getBookByTenSachOrTenTacGia(String tenSach, String tenTacGia);

    Book getBookById(Long id);

    void deleteBookById(Long id);
}
