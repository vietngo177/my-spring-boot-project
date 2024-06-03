package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vietngo.spring.myproject.entity.Author;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Genre;
import vn.vietngo.spring.myproject.service.AuthorService;
import vn.vietngo.spring.myproject.service.BookService;
import vn.vietngo.spring.myproject.service.GenreService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;

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
        model.addAttribute("books", books);
        return "admin/listbook";
    }

    @GetMapping("/create")
    public String createBook(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        return "admin/updatebook";
    }

    @PostMapping("/save")
    public String saveBook(@ModelAttribute Book book, @RequestParam(value = "tenTacGiaMoi") String tacGiaMoi ,@RequestParam(value = "tenTheLoaiMoi") String theLoaiMoi, @RequestParam(value = "hinhanh") MultipartFile multipartFile, Model model) throws IOException {
        if(multipartFile.isEmpty()){
            book.setHinhAnh("null");
        }else{
            String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
            book.setHinhAnh(fileName);
            if(book.getAuthor()==null) {
                Author author = new Author(tacGiaMoi);
                authorService.addAuthor(author);
                book.setAuthor(author);
            }
            if(book.getGenre()==null) {
                Genre genre = new Genre(theLoaiMoi);
                genreService.addGenre(genre);
                book.setGenre(genre);
            }
            Book savedBook = bookService.updateBook(book);
            String uploadDir = "./src/main/resources/static/img/" + savedBook.getId();
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            try (InputStream inputStream = multipartFile.getInputStream()) {
                Path filePath = uploadPath.resolve(fileName);
                System.out.println(filePath.toFile().getAbsoluteFile());
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException ex) {
                throw new IOException("Could not save file: " + fileName, ex);
            }
        }
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        model.addAttribute("message", "Bạn đã cập nhật sách vào cơ sở dữ liệu thành công");
        return "admin/updatebook";
    }

    @GetMapping("/update")
    public String updateBook(@RequestParam("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("genres", genreService.getAllGenre());
        return "admin/updatebook";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id){
        bookService.deleteBookById(id);
        return "redirect:/book/list";
    }


}
