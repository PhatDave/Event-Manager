package com.hackathlon.hackathlon.service;

public interface EmailSender {
    void sendEmail(String to, String subject, String body);
}
