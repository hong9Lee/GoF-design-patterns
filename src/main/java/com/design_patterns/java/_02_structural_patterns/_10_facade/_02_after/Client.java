package com.design_patterns.java._02_structural_patterns._10_facade._02_after;

public class Client {
    public static void main(String[] args) {
        EmailSettings emailSettings = new EmailSettings();
        emailSettings.setHost("127.0.0.1");
        EmailSender emailSender = new EmailSender(emailSettings);

        EmailMessage emailMessage = new EmailMessage();
        emailMessage.setFrom("hong");
        emailMessage.setTo("kee");
        emailMessage.setSubject("greet");
        emailMessage.setText("hello");
        emailSender.sendEmail(emailMessage);
    }
}
