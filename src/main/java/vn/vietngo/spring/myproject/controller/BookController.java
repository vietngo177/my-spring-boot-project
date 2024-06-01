package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.service.AuthorService;
import vn.vietngo.spring.myproject.service.BookService;
import vn.vietngo.spring.myproject.service.GenreService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;

    public BookController() {
    }

    @Autowired
    public BookController(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @GetMapping("")
    public String viewBook(@RequestParam Long id, Model model) {
        Book book = bookService.getBookById(id);
        if(book == null) {
            return "index";
        }
        model.addAttribute("book", book);
        return "viewbook";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Book> books = bookService.getAllBook();
        model.addAttribute("book", new Book());
        model.addAttribute("books", books);
        return "listbook";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        return "updatebook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/book/list";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        return "updatebook";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id){
        bookService.deleteBookById(id);
        return "listbook";
    }

}
