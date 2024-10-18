package tebogomkhize.projects.atmsimulation.account.model;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;


@Entity
public class Transaction {
    String type;

    float amount;

    String accNum;

    LocalDate date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transNumber;

    float postTransBal;

    public Transaction() {}

    public Transaction(
    String type, String accNum, LocalDate date,
    float amount, float postTransBal) {

        this.type = type;
        this.date = date;
        this.amount = amount;
        this.accNum = accNum;
        this.postTransBal = postTransBal;
    }

    // Getters and Setters
    public int getTransNumber() {
        return transNumber;
    }

    public void setTransNumber(int transNumber) {
        this.transNumber = transNumber;
    }

    public String getAccNum() {
        return accNum;
    }

    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }

    public float getPostTransBal() {
        return postTransBal;
    }

    public void setPostTransBal(float postTransBal) {
        this.postTransBal = postTransBal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
