package vn.vietngo.spring.myproject.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.entity.Book;
import vn.vietngo.spring.myproject.entity.Role;
import vn.vietngo.spring.myproject.service.AccountService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private final AccountService accountService;

    @Autowired
    public SignupController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public String signup(Model model){
        Account account = new Account();
        model.addAttribute("account", account);
        return "signup";
    }

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @PostMapping("/save")
    public String register(@Valid @ModelAttribute Account account, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            if(account.getRoles() == null){
                return "signup";
            }else{
                model.addAttribute("book", new Book());
                return "admin/addaccount";
            }
        }
        Account accountExisting = accountService.getAccountByTenDangNhap(account.getTenDangNhap());
        if(accountExisting!=null){
            model.addAttribute("account", new Account());
            model.addAttribute("signupError", "Tên đăng nhập đã tồn tại!");
            if(account.getRoles() == null){
                return "signup";
            }else{
                model.addAttribute("book", new Book());
                return "admin/addaccount";
            }
        }
        account.setMatKhau(new BCryptPasswordEncoder().encode(account.getMatKhau()));
        if(account.getRoles() == null) {
            Role defaultRole = new Role("ROLE_USER");
            List<Role> list = new ArrayList<>();
            list.add(defaultRole);
            account.setRoles(list);
            accountService.addAccount(account);
            session.setAttribute("account", account);
            return "success";
        }else{
            accountService.addAccount(account);
            model.addAttribute("message","Bạn đã đăng ký người dùng mới thành công!");
            model.addAttribute("book", new Book());
            return "/admin/addaccount";
        }


    }
}
