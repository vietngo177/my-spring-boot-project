package vn.vietngo.spring.myproject.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.service.AccountService;

import java.security.Principal;

@Controller
@RequestMapping("/changepassword")
public class ChangePasswordController {
    private AccountService accountService;

    public ChangePasswordController() {
    }

    @Autowired
    public ChangePasswordController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public String changePassword(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        model.addAttribute("newPassword", "");
        model.addAttribute("passwordError1", "");

        return "changepassword";
    }

    @PostMapping("/save")
    public String savePassword(@ModelAttribute Account account, @ModelAttribute String newPassword, Model model, Principal principal) {
        Account account1 = accountService.getAccountByTenDangNhap(principal.getName());
        if(!account1.getMatKhau().equals(account.getMatKhau())) {
            model.addAttribute("account", new Account());
            model.addAttribute("newPassword", "");
            model.addAttribute("passwordError1", "Mật khẩu không chính xác!");
            return "changepassword";
        }
        if(account.getMatKhau().equals(newPassword)){
            model.addAttribute("account", new Account());
            model.addAttribute("newPassword", "");
            model.addAttribute("passwordError2", "Mật khẩu mới không được trùng với mật khẩu hiện tại!");
            return "changepassword";
        }
        account1.setMatKhau(newPassword);
        accountService.updateAccount(account1);
        return "index";
    }
}
