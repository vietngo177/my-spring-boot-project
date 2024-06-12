package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.vietngo.spring.myproject.entity.*;
import vn.vietngo.spring.myproject.service.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;
    private GenreService genreService;
    private CommentService commentService;
    private ReviewService reviewService;

    public BookController() {
    }

    @Autowired
    public BookController(ReviewService reviewService, BookService bookService, AuthorService authorService, GenreService genreService, CommentService commentService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
        this.commentService = commentService;
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public String viewBook(@RequestParam Long id, Model model) {
        Book book = bookService.getBookById(id);
        if(book == null) {
            return "index";
        }
        List<Comment> comments = commentService.getCommentsByBook(id);
        List<Review> reviews = reviewService.getReviewsByBook(id);
        model.addAttribute("comments", comments);
        model.addAttribute("reviews", reviews);
        model.addAttribute("book", book);
        model.addAttribute("listrate", change(reviews));
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

    private ArrayList<Integer> change(List<Review> listReview){
        Integer[] array = {0,0,0,0,0,0};
        ArrayList<Integer> listRate = new ArrayList<>(Arrays.asList(array));
        int rate = 0;
        for(Review i : listReview){
            rate = i.getRate();
            listRate.set(rate, listRate.get(rate) + 1);
        }
        System.out.println(listRate);
        return listRate;
    }
}
