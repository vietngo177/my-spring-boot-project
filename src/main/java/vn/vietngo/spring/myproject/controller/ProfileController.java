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
import vn.vietngo.spring.myproject.service.AccountService;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    private AccountService accountService;

    public ProfileController() {
    }

    @Autowired
    public ProfileController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("")
    public String profile(Model model, Principal principal){
        Account account = accountService.getAccountByTenDangNhap(principal.getName());
        model.addAttribute("account", account);
        return "profile";
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
        return "profile";
    }
}
