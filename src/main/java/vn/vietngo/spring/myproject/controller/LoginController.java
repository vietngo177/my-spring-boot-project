package vn.vietngo.spring.myproject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.service.AccountService;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}