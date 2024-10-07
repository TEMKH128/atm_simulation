package tebogomkhize.projects.atmsimulation.account.service;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    /**
     * Sends email using JavaMailSender to receiving email address.
     * @param to email address of receiver of email.
     * @param subject subject of email.
     * @param text body/text of email.
     */
    @Async
    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("temkh128projects@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        mailSender.send(message);
    }

    /**
     * Sends email with account number and pin for new account holders.
     * @param accNum account number of new account holder.
     * @param pin pin of new account holder.
     * @param email email address of account holder (Used for communication).
     */
    public void newAccEmail(String accNum, String pin, String email) {
        String subject = "New Account Details";

        String emailBody = "Good Day.\n\n" +
                "Please keep note of following details:\n\n" +
                "* Account Number: " + accNum +
                "\n* Account Pin: " + pin +
                "\n\nKind Regards.";

        sendEmail(email, subject, emailBody);
    }

    /**
     * Sends email to receiver of transfer amount.
     * @param email email of receiving account.
     * @param account account in which the transfer originated from.
     * @param amount amount transferred to receiver.
     */
    public void transferReceiverEmail(
        String email, String transferEmail, String account, float amount) {

        String subject = "Amount Transferred into Account";

        String emailBody = "Good Day.\n\n" +
            "An amount of " + amount + " has been transferred into your " +
            "account by account (" + account + ") (." + transferEmail + ")." +
            "\n\nKind Regards.";

        sendEmail(email, subject, emailBody);
    }
}
