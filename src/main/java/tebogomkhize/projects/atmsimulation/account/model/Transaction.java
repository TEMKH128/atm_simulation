package tebogomkhize.projects.atmsimulation.account.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Transaction {
    String type;

    float amount;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transNumber;

    LocalDate transDate;

    public Transaction() {}

    public Transaction(String type, LocalDate transDate, float amount) {
        this.type = type;
        this.transDate = transDate;
        this.amount = amount;
    }

    // Getters and Setters
    public int getTransNumber() {
        return transNumber;
    }

    public void setTransNumber(int transNumber) {
        this.transNumber = transNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getTransDate() {
        return transDate;
    }

    public void setTransDate(LocalDate transDate) {
        this.transDate = transDate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}
