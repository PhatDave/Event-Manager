package com.hackathlon.service.impl;

import com.hackathlon.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class EmailSenderImpl implements EmailSender {
    @Value("${spring.mail.username}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(to);
//        simpleMailMessage.setCc(ccEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(body);

        CompletableFuture.runAsync(() -> javaMailSender.send(simpleMailMessage));
    }
}
