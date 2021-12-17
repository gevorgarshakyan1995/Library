

package com.test.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component("mailSenderClient")
public class MailSender {

    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;

    public void sendSimpleMessage(String to, String subject, String email) throws MessagingException {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String htmlMsg = "VERIFLED\n" + "\n" + "\n" +
                "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "       <title>Title</title>\n" +
                "    </head>\n" +
                "<body>\n" +
                "<form action = \"http://localhost:8080/user/verify?email=" + email + "\"  method = \"POST\" >\n" +
                "\n" +
                "        <input type = \"submit\" value = \"VERIFLED\" />\n" +
                "    </form>\n" +
                "</body>\n" +
                "</html>";
//mimeMessage.setContent(htmlMsg, "text/html"); /** Use this or below line **/
        helper.setText(htmlMsg, true); // Use this or above line.
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom(from);
        emailSender.send(mimeMessage);


    }

    public void tokenSimpleMessage(String to, String subject, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(token);
        emailSender.send(message);
    }

}
