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
import vn.vietngo.spring.myproject.entity.Role;
import vn.vietngo.spring.myproject.service.AccountService;
import vn.vietngo.spring.myproject.service.RoleService;

import java.util.ArrayList;
import java.util.Collection;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private AccountService accountService;
    private RoleService roleService;

    @Autowired
    public SignupController(AccountService accountService, RoleService roleService ) {
        this.accountService = accountService;
        this.roleService = roleService;
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
            return "signup";
        }
        Account accountExisting = accountService.getAccountByTenDangNhap(account.getTenDangNhap());
        if(accountExisting!=null){
            model.addAttribute("account", new Account());
            model.addAttribute("signupError", "Tên đăng nhập đã tồn tại!");
            return "signup";
        }
        account.setMatKhau(new BCryptPasswordEncoder().encode(account.getMatKhau()));
        Role defaultRole = roleService.findByRole("ROLE_USER");
        Collection<Role> list = new ArrayList<Role>();
        list.add(defaultRole);
        account.setRoles(list);
        accountService.addAccount(account);
        session.setAttribute("account", account);

        return "success";
    }
}
