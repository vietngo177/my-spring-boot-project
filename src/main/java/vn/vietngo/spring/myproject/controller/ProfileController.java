package vn.vietngo.spring.myproject.controller;

import jakarta.servlet.http.HttpSession;
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

    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @GetMapping("")
    public String profile(Model model){
        Account account = new Account();
        model.addAttribute("profile", account);
        return "profile";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Account account, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            return "profile";
        }
        accountService.updateAccount(account);
        session.setAttribute("account", account);
        session.setAttribute("account", account);
        return "success";
    }
}
