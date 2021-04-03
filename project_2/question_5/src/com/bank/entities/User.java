package com.bank.entities;


import com.bank.BankingSystem;

import java.util.ArrayList;
import java.util.Objects;

/**
 * The User that can have many accounts
 */
public class User {
    private BankingSystem bank;
    private String name;
    private String nationalId;
    private String password;
    private ArrayList<Account> accounts;

    /**
     * Instantiates a new User with firstname and lastname given as separate strings.
     *
     * @param firstName  the first name
     * @param lastName   the last name
     * @param nationalId the national id
     * @param password   the password
     * @param bank       the bank
     */
    public User(String firstName, String lastName, String nationalId, String password, BankingSystem bank) {
        this(firstName + " " + lastName, nationalId, password, bank);

    }

    /**
     * Instantiates a new User with name given as a string.
     *
     * @param name       the name
     * @param nationalId the national id
     * @param password   the password
     * @param bank       the bank
     */
    public User(String name, String nationalId, String password, BankingSystem bank) {
        this.name = name;
        this.nationalId = nationalId;
        this.password = password;
        this.bank = bank;

        this.accounts = new ArrayList<Account>();
    }

    /**
     * Get user name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets User password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets national user id.
     *
     * @return the national id
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     * Add account to user's accounts.
     *
     * @param account the account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);

//        TODO: update banking system with newly created account
    }


    /**
     * Remove account from user's accounts.
     *
     * @param account the account
     */
    public void removeAccount(Account account) {
        this.accounts.remove(account);

//        TODO: update banking system with recently deleted account
    }

    /**
     * Deposit given account with input amount if the account reference exists in user's accounts list.
     *
     * @param account the account
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void deposit(Account account, int amount) throws Exception {
        if (!this.accounts.contains(account)) {
            throw new Exception("Account does not exist in user's accounts list");
        }

        account.updateBalance(amount);
    }


    /**
     * Deposit given account with input amount if the account reference exists in user's accounts list.
     *
     * @param account the account
     * @param amount  the amount
     * @throws Exception the exception
     */
    public void withdrawal(Account account, int amount) throws Exception {
        if (amount <= 0) {
            throw new Exception("Invalid input amount, amount must be positive.");
        }

        if (!this.accounts.contains(account)) {
            throw new Exception("Account does not exist in user's accounts list");
        }

        account.updateBalance(-1 * amount);
    }

    /**
     * Transfer money from user's account to another account.
     *
     * @param sourceAccount      the source account
     * @param destinationAccount the destination account which can another account owned by the user or other person
     * @param amount             the amount
     * @throws Exception the exception
     */
    public void transfer(Account sourceAccount, Account destinationAccount, int amount) throws Exception {
        if (!this.accounts.contains(sourceAccount)) {
            throw new Exception("source account does not belong to user");
        }

        if (!this.bank.hasAccount(sourceAccount) || !this.bank.hasAccount(destinationAccount)) {
            throw new Exception("Source account or destination account does not exist");
        }

        if (amount > sourceAccount.getBalance()) {
            throw new Exception("Not enough Balance. Money Transfer Canceled!");
        }

        sourceAccount.updateBalance(-1 * amount);
        destinationAccount.updateBalance(amount);
    }

    /**
     * print given account balance if the account is part of the user's accounts.
     *
     * @param account the account
     * @throws Exception the exception
     */
    public void checkBalance(Account account) throws Exception {
        if (!this.accounts.contains(account)) {
            throw new Exception("Access Denied! Can not check another user's account balance");
        }

        System.out.println("Account balance is : " + account.getBalance());
    }

    /**
     * Print all accounts of the user.
     *
     * @throws Exception the exception
     */
    public void printAllAccounts() throws Exception {
        if (this.accounts.size() == 0) {
            throw new Exception("No account has been added");
        }

        int counter = 1;
        for (Account account : this.accounts) {
            System.out.println(counter + ")");
            account.printData();
            counter++;
        }
    }

    /**
     * Print user data.
     */
    public void printUserData() {
        System.out.println("User Data:");
        System.out.println("Name : " + this.name);
        System.out.println("NationalId : " + this.nationalId);
        System.out.println("Accounts count: " + this.accounts.size());
        System.out.println("---------------------------");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return nationalId.equals(user.nationalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nationalId);
    }
}

