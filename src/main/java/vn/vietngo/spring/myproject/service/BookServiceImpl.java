package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.repository.BookRepository;
import vn.vietngo.spring.myproject.entity.Book;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl() {}

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getBookByTenSach(String ten) {
        return bookRepository.findByTenSach(ten);
    }

    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.getReferenceById(id);
    }

    @Override
    public List<Book> getBookByTenSachOrTenTacGia(String tenSach, String tenTacGia) {
        return bookRepository.findByTenSachContainingOrAuthor_HoVaTenContaining(tenSach, tenTacGia);
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }

    @Override
    public void deleteBookById(Long id){
        bookRepository.deleteById(id);
    }
}
