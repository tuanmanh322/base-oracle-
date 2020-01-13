package com.mockapi.mockapi.service.impl;

import com.mockapi.mockapi.model.ConfirmationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SMailSenderImpl {
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendRegistrationMail(ConfirmationToken token) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("Account verification - QLNS");
        message.setFrom("QLNS-Tim07");
        message.setTo(token.getEmployee().getEmail());

        message.setText("Go to this page to activate your account http://localhost:4200/verify?token=" + token.getToken());

        javaMailSender.send(message);
    }
}
