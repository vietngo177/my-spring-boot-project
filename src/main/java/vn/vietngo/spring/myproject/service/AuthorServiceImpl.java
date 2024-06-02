package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.repository.AuthorRepository;
import vn.vietngo.spring.myproject.entity.Author;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;

    public AuthorServiceImpl() {}

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.saveAndFlush(author);
    }

    @Override
    public List<Author> getAllAuthor() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.getReferenceById(id);
    }

    @Override
    public void addAuthor(Author book){
        authorRepository.save(book);
    }

    @Override
    public void deleteAuthorById(Long id){
        authorRepository.deleteById(id);
    }
}
