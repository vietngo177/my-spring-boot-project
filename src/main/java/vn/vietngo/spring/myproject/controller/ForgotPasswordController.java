package vn.vietngo.spring.myproject.controller;

import jakarta.mail.MessagingException;
import net.bytebuddy.utility.RandomString;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.vietngo.spring.myproject.entity.Account;
import vn.vietngo.spring.myproject.service.AccountService;
import vn.vietngo.spring.myproject.service.MailService;

@Controller
public class ForgotPasswordController {
    private AccountService accountService;
    private MailService mailService;

    public ForgotPasswordController() {}

    @Autowired
    public ForgotPasswordController(AccountService accountService, MailService mailService) {
        this.accountService = accountService;
        this.mailService = mailService;
    }

    public static class Utility {
        public static String getSiteURL(HttpServletRequest request) {
            String siteURL = request.getRequestURL().toString();
            return siteURL.replace(request.getServletPath(), "");
        }
    }

    @GetMapping("/forgotpassword")
    public String forgotPassword(){
        return "forgotpassword";
    }

    @PostMapping("/forgotpassword/save")
    public String processForgotPassword(HttpServletRequest request, Model model) throws MessagingException, NullPointerException {
        String token = RandomString.make(20);
        System.out.println(token);
        Account account = accountService.getAccountByEmail(request.getParameter("email"));
        System.out.println(account);
        if(account==null){
            model.addAttribute("message", "Không thể xác nhận tài khoản với email bạn gửi!");
        }else {
            account.setResetPasswordToken(token);
            accountService.updateAccount(account);
            String link = Utility.getSiteURL(request) + "/confirmpassword?token=" + token;
            System.out.println(link);
            mailService.sendEmail(request.getParameter("email"), link);
            model.addAttribute("message", "Link đổi mật khẩu đã được gửi về email. Vui lòng xác nhận!");
        }
        return "forgotpassword";
    }

    @GetMapping("/confirmpassword")
    public String confirmPassword(@RequestParam(value = "token") String token, Model model){
        Account account = accountService.getAccountByResetPasswordToken(token);

        if(account == null){
            model.addAttribute("message", "Đã xảy ra lỗi khi truy xuất đến đường dẫn đổi mật khẩu. Vui lòng thử lại!");
            return "forgotpassword";
        }else{
            model.addAttribute("token",token);
            return "confirmpassword";
        }
    }

    @PostMapping("/confirmpassword/save")
    public String processConfirmPassword(HttpServletRequest request, Model model){
        Account account = accountService.getAccountByResetPasswordToken(request.getParameter("token"));
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        account.setMatKhau(bCryptPasswordEncoder.encode(request.getParameter("matKhau")));
        accountService.updateAccount(account);
        model.addAttribute("message", "Bạn đã đổi mật khẩu thành công");
        return "confirmpassword";
    }
}
