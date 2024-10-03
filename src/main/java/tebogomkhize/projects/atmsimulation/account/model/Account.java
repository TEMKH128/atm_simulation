package tebogomkhize.projects.atmsimulation.account.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Account {
    String age;
    String pin;
    String name;
    String email;
    float balance;
    @Id
    String accountNum;

    public Account() {}

    public Account(
        String name, String age, String email, String accountNum,
        String pin, float balance) {

        this.pin = pin;
        this.age = age;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.accountNum = accountNum;
    }

    // Getters and Setters
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
