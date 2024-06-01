package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

public interface BookService {
    void addBook(Book book);

    List<Book> getBookByTenSach(String tenSach);

    List<Book> getAllBook();

    Book getBookById(Long id);

    void deleteBookById(Long id);
}
