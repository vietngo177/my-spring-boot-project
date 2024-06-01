package vn.vietngo.spring.myproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String changePassword() {
        return "changepassword";
    }

    @PostMapping("/save")
    public String savePassword(@RequestParam String matKhauHienTai, @RequestParam String matKhau, Model model, Principal principal) {
        Account account1 = accountService.getAccountByTenDangNhap(principal.getName());
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(!bCryptPasswordEncoder.matches(matKhauHienTai, account1.getMatKhau())) {
            model.addAttribute("passwordError1", "Mật khẩu không chính xác!");
        }
        else if(matKhau.equals(matKhauHienTai)){
            model.addAttribute("passwordError2", "Mật khẩu mới không được trùng với mật khẩu hiện tại!");
        }else{
            account1.setMatKhau(bCryptPasswordEncoder.encode(matKhau));
            accountService.updateAccount(account1);
            model.addAttribute("message", "Bạn đã đổi mật khẩu thành công!");
        }
        return "changepassword";
    }
}
