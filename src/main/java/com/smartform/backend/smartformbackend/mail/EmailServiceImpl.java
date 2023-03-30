package com.smartform.backend.smartformbackend.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailServiceImpl implements EmailService{
    private static final String NOREPLY_ADDRESS = "noreply@quantum.com";

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        send(message);
    }

    @Override
    public void sendMessage(SimpleMailMessage message) {
        message.setFrom(NOREPLY_ADDRESS);
        send(message);
    }

    private void send(SimpleMailMessage message) {
        try {
            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
        }
    }
}
