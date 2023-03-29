package com.smartform.backend.smartformbackend.mail;

public interface EmailService {
    void sendSimpleMessage(String to,
                           String subject,
                           String text);
}
