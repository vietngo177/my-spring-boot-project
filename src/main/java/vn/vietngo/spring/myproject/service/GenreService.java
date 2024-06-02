package vn.vietngo.spring.myproject.service;

import vn.vietngo.spring.myproject.entity.Genre;

import java.util.List;

public interface GenreService {
    void addGenre(Genre genre);

    Genre updateGenre(Genre genre);

    List<Genre> getAllGenre();

    Genre getGenreById(Long id);

    void deleteGenreById(Long id);
}

