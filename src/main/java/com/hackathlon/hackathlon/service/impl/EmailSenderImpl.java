package com.hackathlon.hackathlon.service.impl;

import com.hackathlon.hackathlon.service.EmailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailSenderImpl implements EmailSender {
    @Value("${spring.mail.from.email}")
    private String fromEmail;

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail(String to, String text, String subject) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(fromEmail);
        simpleMailMessage.setTo(to);
//        simpleMailMessage.setCc(ccEmail);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        javaMailSender.send(simpleMailMessage);
    }
}