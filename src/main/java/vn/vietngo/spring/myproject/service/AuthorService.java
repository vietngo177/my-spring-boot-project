package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Author;

import java.util.List;

public interface AuthorService {
    void addAuthor(Author author);

    List<Author> getAllAuthor();

    Author getAuthorById(Long id);

    void deleteAuthorById(Long id);
}

