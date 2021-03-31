package com.bank.entities;

import java.util.*;

/**
 * An account in the banking system.
 */
public class Account {
    private final String[] allowedTypes = new String[]{"saving", "checking"};
    private final UUID serial;
    private final ArrayList<Transaction> transactions;
    private final String nationalId;
    private String type;
    private String name;
    private double balance;

    /**
     * Instantiates a new Account with firstname and lastname separated.
     *
     * @param type       account type which can be saving or checking
     * @param nationalId the user nationalId
     * @param firstName  the user firstName
     * @param lastName   the user lastName
     * @param balance    the account balance
     * @throws Exception the exception
     */
    public Account(String type, String nationalId, String firstName, String lastName, double balance) throws Exception {
        this(type, nationalId, firstName + " " + lastName, balance);
    }

    /**
     * Instantiates a new Account with given name as a string.
     *
     * @param type       the type
     * @param nationalId the national id
     * @param name       the name
     * @param balance    the balance
     * @throws Exception the exception
     */
    public Account(String type, String nationalId, String name, double balance) throws Exception {
        this.setType(type);
        this.setName(name);

        this.nationalId = nationalId;
        this.balance = balance;

        this.transactions = new ArrayList<Transaction>();
        this.serial = UUID.randomUUID();
    }

    /**
     * Gets account serial or uuid.
     *
     * @return the serial
     */
    public String getSerial() {
        return serial.toString();
    }

    /**
     * Set Account type.
     *
     * @param type the type
     * @throws Exception the exception
     */
    public void setType(String type) throws Exception {
        if (!Arrays.asList(this.allowedTypes).contains(type.toLowerCase(Locale.ROOT))) {
            throw new Exception("invalid account type, account type can be saving or checking");
        }

        this.type = type;
    }


    /**
     * Set account user name with name given as a string
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set user name with firstname and lastname as separate strings.
     */
    public void setName(String firstName, String lastName) {
        this.name = firstName + " " + lastName;
    }

    /**
     * Update account balance with given amount and add a transaction to the list of transactions.
     *
     * @param amount the amount
     * @throws Exception the exception
     */
    public void updateBalance(double amount) throws Exception {
        if (amount == 0) {
            throw new Exception("Can not update account balance with amount zero");
        }
        if (amount < 0 && Math.abs(amount) > this.balance) {
            throw new Exception("Amount can not be negative!");
        }

        Transaction transaction = new Transaction(amount);
        this.transactions.add(transaction);
        this.balance += amount;
    }

    /**
     * Add transaction to the list of transactions and update account balance.
     *
     * @param transaction the transaction
     */
    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        this.balance += transaction.getAmount();
    }

    /**
     * Print all transactions related to the account.
     */
    public void printTransactions() {
        for (Transaction transaction : this.transactions) {
            transaction.print();
        }
    }


    /**
     * Print account data.
     */
    public void printData() {
        System.out.println("----------------------------");
        System.out.println("Account Data: ");
        System.out.println("uuid : " + this.serial.toString());
        System.out.println("balance : " + this.balance);
        System.out.println("type : " + this.type);
        System.out.println("user name: " + this.name);
        System.out.println("user national id : " + this.nationalId);
        System.out.println("----------------------------");
    }

    public double getBalance() {
        return balance;
    }
}
