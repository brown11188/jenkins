package com.enclaveit.brea.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendEmailService {
    @Autowired
    private MailSender mailSender;

    public void serviceReadyToSendEmail(String toAddress, String subject, String msgBody) {

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(toAddress);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(msgBody);

        mailSender.send(simpleMailMessage);
    }

}
