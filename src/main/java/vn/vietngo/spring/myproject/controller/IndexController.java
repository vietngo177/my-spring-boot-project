package vn.vietngo.spring.myproject.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vn.vietngo.spring.myproject.entity.Account;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
