package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Book;

public interface BookService {
    Book getBookByTenSach(String tenSach);
}
