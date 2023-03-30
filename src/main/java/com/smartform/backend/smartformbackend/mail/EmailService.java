package com.smartform.backend.smartformbackend.mail;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);

    void sendMessage(SimpleMailMessage message);
}
