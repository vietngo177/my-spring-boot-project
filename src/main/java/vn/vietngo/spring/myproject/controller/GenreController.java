package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Genre;
import vn.vietngo.spring.myproject.service.GenreService;

import java.util.List;

@Controller
@RequestMapping("/genre")
public class GenreController {
    private GenreService genreService;

    public GenreController() {
    }

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Genre> genres = genreService.getAllGenre();
        model.addAttribute("book", new Book());
        model.addAttribute("genres", genres);
        return "admin/listgenre";
    }

    @GetMapping("/create")
    public String createGenge(Model model) {
        Genre genre = new Genre();
        model.addAttribute("genre", genre);
        model.addAttribute("book", new Book());
        return "admin/updategenre";
    }

    @PostMapping("/save")
    public String saveGenre(@ModelAttribute Genre genre, Model model){
        Genre savedGenre = genreService.updateGenre(genre);
        model.addAttribute("book", new Book());
        model.addAttribute("message", "Bạn đã cập nhật thể loại vào cơ sở dữ liệu thành công");
        return "admin/updategenre";
    }

    @GetMapping("/update")
    public String updateGenre(@RequestParam("id") Long id, Model model) {
        Genre savedGenre = genreService.getGenreById(id);
        model.addAttribute("genre", savedGenre);
        model.addAttribute("book", new Book());
        return "admin/updategenre";
    }

    @GetMapping("/delete")
    public String deleteGenre(@RequestParam("id") Long id){
        genreService.deleteGenreById(id);
        return "redirect:/genre/list";
    }

}
