package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.vietngo.spring.myproject.entity.Author;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.service.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {
    private AuthorService authorService;

    public AuthorController() {
    }

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Author> authors = authorService.getAllAuthor();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "admin/listauthor";
    }

    @GetMapping("/create")
    public String createAuthor(Model model) {
        Author author = new Author();
        model.addAttribute("author", author);
        model.addAttribute("book", new Book());
        return "admin/updateauthor";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Author author, Model model){
        Author savedAuthor = authorService.updateAuthor(author);
        model.addAttribute("book", new Book());
        model.addAttribute("message", "Bạn đã cập nhật tác giả vào cơ sở dữ liệu thành công");
        return "admin/updateauthor";
    }

    @GetMapping("/update")
    public String updateAuthor(@RequestParam("id") Long id, Model model) {
        Author author = authorService.getAuthorById(id);
        model.addAttribute("author", author);
        model.addAttribute("book", new Book());
        return "admin/updateauthor";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id){
        authorService.deleteAuthorById(id);
        return "redirect:/author/list";
    }
}
