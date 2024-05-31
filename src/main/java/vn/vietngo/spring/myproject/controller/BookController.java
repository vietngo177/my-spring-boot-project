package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.service.BookService;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;

    public BookController() {
    }

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public String viewBook(@RequestParam Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "viewbook";
    }


}
