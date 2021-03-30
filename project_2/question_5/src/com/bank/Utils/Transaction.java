package com.bank.Utils;

import java.util.Date;

/**
 * A transaction with a amount and a date.
 */
public final class Transaction {
    private final double amount;
    private final Date date;

    /**
     * Instantiates a new Transaction.
     *
     * @param amount the amount
     * @throws Exception the exception
     */
    public Transaction(double amount) throws Exception {
        if (amount == 0) {
            throw new Exception("Transaction amount can't be zero");
        }

        this.amount = amount;
        this.date = new Date();
    }

    /**
     * Get transaction amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Get transaction date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * print transaction data.
     */
    public void print() {
        System.out.println("---------------------------------");
        if (this.amount > 0) {
            System.out.println("Transaction amount : " + this.amount);
            System.out.println("Transaction type : " + "Deposit");
        } else {
            System.out.println("Transaction amount : " + Math.abs(this.amount));
            System.out.println("Transaction type : " + "Withdraw");
        }

        System.out.println("Transaction Date : " + this.date.toString());
        System.out.println("---------------------------------");
    }
}
