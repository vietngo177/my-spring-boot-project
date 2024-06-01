package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.service.BookService;

import java.util.List;

@Controller
public class IndexController {
    private BookService bookService;

    public IndexController() {}

    @Autowired
    public IndexController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = {"/","/index"})
    @Cacheable(value = "booklist")
    public String index(Model model) {
        List<Book> list = bookService.getAllBook();
        model.addAttribute("books", list);
        model.addAttribute("book", new Book());
        return "index";
    }

    @GetMapping("/index/find")
    public String index(@ModelAttribute Book book, Model model) {
        List<Book> list = bookService.getBookByTenSach(book.getTenSach());
        model.addAttribute("books", list);
        return "index";
    }

    @Scheduled(fixedRate = 300*1000)
    @CacheEvict(value = "list", allEntries = true)
    public void clearAllCache(){}
}
