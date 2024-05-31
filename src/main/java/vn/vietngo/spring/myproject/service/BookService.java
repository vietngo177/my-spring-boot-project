package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getBookByTenSach(String tenSach);

    List<Book> getAllBook();

    Book getBookById(Long id);
}
