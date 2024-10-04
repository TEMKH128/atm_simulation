package tebogomkhize.projects.atmsimulation.account.service;

import java.util.*;

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
     * @param firstName First name of potential account-holder.
     * @param lastName Last name of potential account-holder.
     * @param age age of potential account-holder.
     * @param email email of potential account-holder.
     * @return ResponseDTO reflecting outcome of account creation attempt.
     */
    public ResponseDTO createAccount(
        String firstName, String lastName, int age, String email) {

        if (doesAccountExist(firstName.trim(), lastName.trim(), age,
            email.trim())) {

            return new ResponseDTO("ERROR", "Account with provided details " +
                "(name and email) already exists", new HashMap<>());
        }

        if (! isPersonOldEnough(age)) {
            return new ResponseDTO("ERROR", "Age (" + age +
                    ") not old enough to create account", new HashMap<>());
        }

        String accNum = generateUniqueAccNum();
        Account newAcc = createNewAcc(accNum, firstName, lastName, email);
        this.accRepo.save(newAcc);

        sendEmail(newAcc);

        HashMap<String, Object> data = new HashMap<>();
        data.put("account", newAcc);

        return new ResponseDTO(
            "OK", "New account created. Take note of account number and pin.",
                data);
    }

    /**
     * Determines whether age provided is old enough (At least 18) to
     * create an ATM account.
     * @param age age of person to be verified.
     * @return boolean representing outcome of age check.
     */
    public boolean isPersonOldEnough(int age) {
        return age > 17;
    }

    /**
     * Determines whether an account with provided details already exists.
     * @param firstName First name of potential account holder.
     * @param lastName Last name of potential account holder.
     * @param age age of potential account holder.
     * @param email email of potential account holder.
     * @return boolean reflecting whether accounts exists or not.
     */
    public boolean doesAccountExist(
        String firstName, String lastName, int age, String email) {

        return this.accRepo.findByFirstNameAndLastNameAndEmail(
            firstName, lastName, email).isPresent();
    }

    /**
     * Determines whether an account with provided account number exists.
     * @param accountNum account number to be checked.
     * @return boolean reflecting whether account exists or not.
     */
    public boolean doesAccountExist(String accountNum) {
        return this.accRepo.findById(accountNum).isPresent();
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
     * @param firstName First name of account holder.
     * @param lastName Last name of account holder.
     * @param email Email of account holder.
     * @return Returns newly created account.
     */
    public Account createNewAcc(
        String accNum, String firstName, String lastName, String email) {

        String pin = generateAccPinNum(4);
        int openingBal = 0;

        return new Account(firstName, lastName, email,
            accNum, pin, openingBal);
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

    /**
     * Attempts to retrieve account balance of provided account number if
     * account exists.
     * @param accountNum account number to check balance against.
     * @return ResponseDTO reflecting outcome of account balance check.
     */
    public ResponseDTO getAccBal(String accountNum) {
        if (! doesAccountExist(accountNum)) {
            return new ResponseDTO("ERROR", "Account (" + accountNum +
                    ") doesn't exist", new HashMap<>());
        }

        HashMap<String, Object> data = new HashMap<>();
        data.put("balance",
            this.accRepo.findById(accountNum).get().getBalance());

        return new ResponseDTO(
                "OK", "Balance retrieved", data);
    }

    /**
     * Deposit specified amount into account if account exists.
     * @param accountNum account number where amount will be deposited.
     * @param amount amount to be deposited into account.
     * @return ResponseDTO reflecting outcome of attempted deposit into account.
     */
    public ResponseDTO depositMoney(String accountNum, float amount) {
        if (! doesAccountExist(accountNum)) {
            return new ResponseDTO("ERROR", "Account (" + accountNum +
                    ") doesn't exist", new HashMap<>());
        }

        Account account = this.accRepo.findById(accountNum).get();
        account.setBalance(account.getBalance() + amount);
        this.accRepo.save(account);

        HashMap<String, Object> data = new HashMap<>();
        data.put("balance",
            this.accRepo.findById(accountNum).get().getBalance());

        return new ResponseDTO(
            "OK", "Amount (" + amount + ") deposited", data);
    }
}
