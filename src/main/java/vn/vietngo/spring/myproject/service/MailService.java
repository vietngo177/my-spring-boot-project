package vn.vietngo.spring.myproject.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    @Autowired
    private MailService(JavaMailSender mailSender){
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, String link)
            throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String content = "<p>Bạn vừa gửi yêu cầu thay đổi mật khẩu</p>"
                + "<p>Click vào đường dẫn bên dưới để sang trang web đổi mật khẩu:</p>"
                + "<p><a href=\"" + link + "\">Đổi mật khẩu</a></p>"
                + "<br>"
                + "<p>Nếu bạn không phải là người gửi yêu cầu này, hãy đổi mật khẩu tài khoản ngay lập tức để tránh việc bị truy cập trái phép.</p>";
        helper.setTo(email);
        String subject = "Yêu cầu thay đổi mật khẩu";
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
    }
}
