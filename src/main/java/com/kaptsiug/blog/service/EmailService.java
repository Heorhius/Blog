package com.kaptsiug.blog.service;

import com.kaptsiug.blog.entity.redis.UserInvitation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;

    public void sendSimpleMessage(UserInvitation saved) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("heorhi.kaptsiug@gmail.com");
        message.setTo(saved.getEmail());
        message.setSubject("Link for registration");
        //TODO Email template
        message.setText(String.format("<HTML> http://localhost:8080/auth/confirm/%s </HTML>", saved.getInvitationCode()));
        emailSender.send(message);
    }
}