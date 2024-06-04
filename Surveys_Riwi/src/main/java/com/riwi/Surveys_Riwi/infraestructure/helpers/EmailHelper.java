package com.riwi.Surveys_Riwi.infraestructure.helpers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.var;

@Component
@AllArgsConstructor
public class EmailHelper {
    private final JavaMailSender mailSender;

    public void sendMail(String name, String title) {
        MimeMessage message = mailSender.createMimeMessage();

        String htmlContent = this.readHTMLTemplate(name, title);

        try {
            message.setFrom(new InternetAddress("keity1897@gmail.com"));
            message.setSubject("Sending treatment data");

            message.setRecipients(MimeMessage.RecipientType.TO, title);
            message.setContent(htmlContent, MediaType.TEXT_HTML_VALUE);

            mailSender.send(message);
            System.out.println("Email sent");

        } catch (Exception e) {
            System.out.println("ERROR it was not possible to send the email " + e.getMessage());
        }
    }

    private String readHTMLTemplate(String name, String title){

        final Path path = Paths.get("src/main/resources/emails/email_template.html");

        try(var lines = Files.lines(path)){
            var html = lines.collect(Collectors.joining());

            return html.replace("{name}", name).replace("{title}", title);
        } catch (IOException e){
            System.out.println("It was not possible to read the html");
            throw new RuntimeException();
        }
    }
}
