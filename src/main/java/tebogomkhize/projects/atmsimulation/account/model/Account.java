package tebogomkhize.projects.atmsimulation.account.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;


@Entity
public class Account {

    String pin;

    String email;

    float balance;

    String lastName;

    String firstName;

    @Id
    String accountNum;

    public Account() {}

    public Account(
        String firstName, String lastName, String email,
        String accountNum, String pin, float balance) {

        this.pin = pin;
        this.balance = balance;
        this.email = email.trim();
        this.accountNum = accountNum;
        this.lastName = lastName.trim();
        this.firstName = firstName.trim();
    }

    // Getters and Setters
    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
}
