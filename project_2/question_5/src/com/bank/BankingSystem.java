package com.bank;

import com.bank.entities.Account;
import com.bank.entities.User;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The banking system.
 */
public class BankingSystem {
    private ArrayList<User> users;
    private ArrayList<Account> accounts;

    /**
     * Instantiates a new Banking system.
     */
    public BankingSystem() {
        this.users = new ArrayList<User>();
        this.accounts = new ArrayList<Account>();
    }

    /**
     * Register user with firstname and last as separate strings and add it to the bank user list
     *
     * @param firstName  the first name
     * @param lastName   the last name
     * @param nationalId the national id
     * @param password   the password
     * @return the user
     */
    public User register(String firstName, String lastName, String nationalId, String password) throws Exception {
        User user = new User(firstName, lastName, nationalId, password);

        for (User iterationUser : this.users) {
            if (iterationUser.getNationalId().equals(user.getNationalId())) {
                throw new Exception("user already exists");
            }
        }

        this.users.add(user);
        return user;
    }

    /**
     * Register user with name as a string and add it to the bank user list
     *
     * @param name       the name
     * @param nationalId the national id
     * @param password   the password
     * @return the user
     */
    public User register(String name, String nationalId, String password) throws Exception {
        User user = new User(name, nationalId, password);

        for (User iterationUser : this.users) {
            if (iterationUser.getNationalId().equals(user.getNationalId())) {
                throw new Exception("user already exists!");
            }
        }

        this.users.add(user);
        return user;
    }


    /**
     * Add user to the bank list
     *
     * @param user the user
     */
    public void addUser(User user) throws Exception {
        if (this.users.contains(user)) {
            throw new Exception("user already exist");
        }

        this.users.add(user);
    }

    /**
     * Login to user with input national nationalId and password
     *
     * @param nationalId the nationalId
     * @param password   the password
     * @throws Exception the exception
     */
    public User login(String nationalId, String password) throws Exception {
        Iterator<User> userIterator = this.users.iterator();
        while (userIterator.hasNext()) {
            User user = userIterator.next();

            if (user.getNationalId().equals(nationalId) && user.getPassword().equals(password)) {
                System.out.println("User has logged in");
                return user;
            }
        }

        throw new Exception("Invalid nationalId or password");
    }

    /**
     * Remove user form bank system.
     *
     * @param user the user
     * @throws Exception the exception
     */
    public void removeUser(User user) throws Exception {
        if (!this.users.contains(user)) {
            throw new Exception("User does not exist in the banking system");
        }

        this.users.remove(user);
    }

    public void removeUser(String nationalId) throws Exception {
        for (User user : this.users) {
            if (user.getNationalId().equals(nationalId)) {
                this.users.remove(user);
            }
        }

        throw new Exception("User does not exist in the banking system");
    }


    /**
     * Display all users added to banking system account
     */
    public void displayUsers() {
        if (this.users.size() == 0) {
            System.out.println("No User has been added yet!");
            return;
        }

        for (User user : this.users) {
            user.printUserData();
        }
    }

    /**
     * Add account to the banking system.
     *
     * @param account the account
     */
    public void addAccount(Account account) {
        this.accounts.add(account);
    }

    /**
     * Remove account from the banking system.
     *
     * @param account the account
     */
    public void removeAccount(Account account) {
        this.accounts.remove(account);
    }

    /**
     * Display all accounts of the banking system.
     */
    public void displayAccounts() {
        if (this.accounts.size() == 0) {
            System.out.println("No account has been added yet!");
            return;
        }
        for (Account account : this.accounts) {
            account.printData();
        }
    }

    /**
     * Find account account if it exists the account will be returned, if it doesn't an exception will be thrown.
     *
     * @param serial the serial
     * @return the account
     * @throws Exception the exception for when cannot find account in the accounts list
     */
    public Account findAccount(String serial) throws Exception {
        for (Account account : this.accounts) {
            if (account.getSerial().equals(serial)) {
                return account;
            }
        }

        throw new Exception("No account with given serial found");
    }

}