package com.example.demo.service.emailService;

import com.example.demo.Entity.Users;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import java.io.UnsupportedEncodingException;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final EmailProperties emailProperties;  // ← inject this

    public EmailService(JavaMailSender mailSender,
                        EmailProperties emailProperties) {
        this.mailSender       = mailSender;
        this.emailProperties  = emailProperties;
    }

    public void sendHtmlEmail(String to, String subject, String htmlBody)
            throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        try {
            helper.setFrom(new InternetAddress(
                    emailProperties.getSenderAddress(),   // ← use via getter
                    emailProperties.getSenderName()
            ));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlBody, true);

        mailSender.send(message);
    }

    /** Approach 1: Inline HTML string */
    public void sendBirthdayEmail_InlineHtml(Users user)
            throws MessagingException {


        String htmlBody = "<!DOCTYPE html>" +
                "<html><body style='font-family:Arial,sans-serif;background:#f4f4f4;'>" +
                "  <div style='max-width:560px;margin:40px auto;background:#fff;" +
                "       border-radius:12px;overflow:hidden;'>" +
                "    <div style='background:#6db33f;padding:32px;text-align:center;'>" +
                "      <h1 style='color:#fff;margin:0;'>🎂 Happy Birthday!</h1>" +
                "    </div>" +
                "    <div style='padding:32px;'>" +
                "      <p>Hello <strong>" + user.getUsername() + "</strong>,</p>" +
                "      <p>Wishing you a wonderful birthday from all of us!</p>" +
                "      <p>— The Team</p>" +
                "    </div>" +
                "  </div>" +
                "</body></html>";


        sendHtmlEmail(
                user.getEmail(),
                "🎂 Happy Birthday, " + user.getUsername() + "!",
                htmlBody
        );
    }
}
