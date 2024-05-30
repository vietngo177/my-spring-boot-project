package vn.vietngo.spring.myproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.dao.BookRepository;
import vn.vietngo.spring.myproject.entity.Book;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl() {}

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book getBookByTenSach(String tenSach) {
        return bookRepository.findByTenSach(tenSach);
    }
}
