package tebogomkhize.projects.atmsimulation.account.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import tebogomkhize.projects.atmsimulation.account.model.Account;
import tebogomkhize.projects.atmsimulation.account.model.dto.ResponseDTO;
import tebogomkhize.projects.atmsimulation.account.respository.AccountRepository;


@Service
public class AccountService {
    AccountRepository accRepo;
    EmailService emailService;

    @Autowired
    public AccountService(AccountRepository accRepo, EmailService emailService) {
        this.accRepo = accRepo;
        this.emailService = emailService;
    }

    /**
     * Attempts to create an account (New users) with provided details.
     * Verifies that user with provided details doesn't already exist before
     * creation of account.
     * @param name name of potential account-holder.
     * @param age age of potential account-holder.
     * @param email email of potential account-holder.
     * @return ResponseDTO reflecting outcome of account creation attempt.
     */
    public ResponseDTO createAccount(String name, String age, String email) {
        if (doesAccountExist(name, age, email)) {
            return new ResponseDTO("ERROR", "Account with provided details " +
                "(name, age and email) already exists", new ArrayList<>());
        }

        String accNum = generateUniqueAccNum();
        Account newAcc = createNewAcc(accNum, name, age, email);
        this.accRepo.save(newAcc);

        sendEmail(newAcc);

        List<Object> data = new ArrayList<>();
        data.add(newAcc);

        return new ResponseDTO(
            "OK", "New account created. Take note of account number and pin.",
                data);
    }

    /**
     * Determines whether an account with provided details already exists.
     * @param name name of potential account holder.
     * @param age age of potential account holder.
     * @param email email of potential account holder.
     * @return boolean reflecting whether accounts exists or not.
     */
    public boolean doesAccountExist(String name, String age, String email) {
        return this.accRepo.findByNameAndAgeAndEmail(
            name, age, email).isPresent();
    }

    /**
     * Generates a number, which will represent the pin / account number,
     * of specified length.
     * @param genLength length (how many digits) of number to be generated.
     * @return number with specified (length) number of digits.
     */
    public String generateAccPinNum(int genLength) {
        StringBuilder accNumStr = new StringBuilder();
        Random random = new Random();

        while (accNumStr.length() < genLength) {
            accNumStr.append(random.nextInt(10));
        }

        return accNumStr.toString();
    }

    /**
     * Generates a unique account number for an account.
     * @return generated account number.
     */
    public String generateUniqueAccNum() {
        String accNum = null;
        do {
            accNum = generateAccPinNum(6);

        } while (this.accRepo.findById(accNum).isPresent());

        return accNum;
    }

    /**
     * Creates a new account with provided details (arguments passed).
     * @param accNum Unique account number for account to be created.
     * @param name Name of account holder.
     * @param age Age of account holder.
     * @param email Email of account holder.
     * @return Returns newly created account.
     */
    public Account createNewAcc(String accNum, String name, String age, String email) {
        String pin = generateAccPinNum(4);
        int openingBal = 0;

        return new Account(name, age, email, accNum, pin, openingBal);
    }

    public void sendEmail(Account account) {
        String subject = "New Account Details";

        String emailBody = "Good Day.\n\n" +
            "Please keep note of following details:\n\n" +
            "* Account Number: " + account.getAccountNum() +
            "\n* Account Pin: " + account.getPin() +
            "\n\nKind Regards.";

        this.emailService.sendEmail(
            account.getEmail(), subject, emailBody);
    }
}
