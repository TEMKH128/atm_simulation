package tebogomkhize.projects.atmsimulation.account.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;


@Entity
public class Account {
    int age;

    String pin;

    String email;

    float balance;

    String lastName;

    String firstName;

    @Id
    String accountNum;

    public Account() {}

    public Account(
        String firstName, String lastName, int age, String email,
        String accountNum, String pin, float balance) {

        this.pin = pin;
        this.age = age;
        this.email = email.trim();
        this.balance = balance;
        this.accountNum = accountNum;
        this.lastName = lastName.trim();
        this.firstName = firstName.trim();
    }

    // Getters and Setters
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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
