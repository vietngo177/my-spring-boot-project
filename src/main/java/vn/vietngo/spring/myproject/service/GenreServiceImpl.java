package vn.vietngo.spring.myproject.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.vietngo.spring.myproject.repository.GenreRepository;
import vn.vietngo.spring.myproject.entity.Genre;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl implements GenreService {
    private GenreRepository genreRepository;

    public GenreServiceImpl() {}

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return genreRepository.saveAndFlush(genre);
    }

    @Override
    public List<Genre> getAllGenre() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepository.getReferenceById(id);
    }

    @Override
    public void addGenre(Genre book){
        genreRepository.save(book);
    }

    @Override
    public void deleteGenreById(Long id){
        genreRepository.deleteById(id);
    }
}
