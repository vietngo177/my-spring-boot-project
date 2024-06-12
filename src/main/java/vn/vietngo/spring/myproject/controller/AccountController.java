package vn.vietngo.spring.myproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Role;
import vn.vietngo.spring.myproject.service.AccountService;
import vn.vietngo.spring.myproject.service.BookService;
import vn.vietngo.spring.myproject.service.RoleService;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/account")
public class AccountController {
    private AccountService accountService;
    private BookService bookService;
    private RoleService roleService;

    public AccountController() {
    }

    @Autowired
    public AccountController(AccountService accountService, RoleService roleService, BookService bookService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.bookService = bookService;
    }

    @GetMapping("")
    public String profile(Model model, Principal principal){
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        model.addAttribute("account", account);
        return "user/account";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Account account, BindingResult result, Model model, Principal principal){
        Account account1 = accountService.getAccountByTenDangNhap(principal.getName());
        if(result.hasErrors()){
            model.addAttribute("account", account);
        }else {
            account1.setHoVaTen(account.getHoVaTen());
            account1.setEmail(account.getEmail());
            accountService.updateAccount(account1);
            model.addAttribute("message", "Bạn đã cập nhật thông tin thành công!");
        }
        return "user/account";
    }

    @GetMapping("/list")
    public String authorize(Model model){
        List<Account> accounts = accountService.getAllAccount();
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        model.addAttribute("accounts", accounts);
        model.addAttribute("authorize", new Account());
        return "admin/authorizeaccount";
    }

    @GetMapping("/add")
    public String addAccount(Model model){
        Account account = new Account();
        List<Role> roles = roleService.getAllRole();
        model.addAttribute("roles", roles);
        model.addAttribute("account", account);
        return "admin/addaccount";
    }

    @PostMapping("/authorize")
    public String authorize(@RequestParam(value = "role") List<Role> role,@RequestParam(value = "id") Long id, Model model, Principal principal){
        List<Account> accounts = accountService.getAllAccount();
        Optional<Account> account = accountService.getAccountById(id);
        List<Role> roles = roleService.getAllRole();
        if(principal.getName().equals(account.get().getTenDangNhap())){
            model.addAttribute("message", "Bạn không thể phân quyền cho chính mình!");
        }else {
            model.addAttribute("message", "Bạn đã phân quyền thành công cho người dùng!");
            account.get().setRoles(role);
            accountService.updateAccount(account.get());
        }
        model.addAttribute("roles", roles);
        model.addAttribute("accounts", accounts);
        return "admin/authorizeaccount";
    }

    @GetMapping("/delete")
    public String deleteAccount(@RequestParam("id") Long id, Principal principal, Model model){
        List<Account> accounts = accountService.getAllAccount();
        List<Role> roles = roleService.getAllRole();
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        if(!Objects.equals(account.getId(), id)) {
            accountService.deleteAccountById(id);
            model.addAttribute("message", "Bạn đã xóa tài khoản người dùng thành công");
        }else{
            model.addAttribute("message", "Bạn không thể xóa tài khoản của chính bạn");
        }
        model.addAttribute("roles", roles);
        model.addAttribute("accounts", accounts);
        model.addAttribute("authorize", new Account());
        return "admin/authorizeaccount";
    }

    @GetMapping("/favoritebook")
    public String favoriteBook(Principal principal, Model model){
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        model.addAttribute("books", account.getBooks());
        return "user/listfavoritebook";
    }

    @GetMapping("/favoritebook/add")
    public String addFavoriteBook(@RequestParam("id") Long id, Principal principal, Model model){
        Book book = bookService.getBookById(id);
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        List<Book> list = account.getBooks();
        if(!list.contains(book)) {
            list.add(book);
            account.setBooks(list);
            accountService.updateAccount(account);
            model.addAttribute("message", "Bạn đã thêm sách vào thư viện thành công");

        }
        return "redirect:/";
    }

    @GetMapping("/favoritebook/delete")
    public String deleteFavoriteBook(@RequestParam("id") Long id, Principal principal){
        Book book = bookService.getBookById(id);
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        List<Book> list = account.getBooks();
        list.remove(book);
        account.setBooks(list);
        accountService.updateAccount(account);
        return "redirect:/account/favoritebook";
    }
}
