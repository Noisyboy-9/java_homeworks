package com.bank;


import com.bank.entities.Account;
import com.bank.entities.User;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    private static BankingSystem bank = new BankingSystem();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        int order;
        do {
            showMainMenu();
            order = getUserOrder();

            switch (order) {
                case 1:
                    signUp();
                    break;
                case 2:
                    signIn();
                    break;
                case 3:
                    admin();
                    break;
                case 4:
                    System.out.println("hope you have enjoyed :)");
                    break;
                default:
                    System.out.println("Unknown Command! Please try again");
            }
        } while (order != 4);
    }

    private static void signIn() throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sing In");

        System.out.print("Please input user national id: ");
        String nationalId = scanner.nextLine();

        System.out.print("Please input user password: ");
        String password = scanner.nextLine();

        User user;
        try {
            user = bank.login(nationalId, password);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return;
        }

        int order;
        do {
            showUserPanel();
            order = getUserOrder();
            Account account;
            switch (order) {
                case 1:
                    try {
                        user.printAllAccounts();
                        account = getUserAccountChoose();
                        handleUserAccountOrder(user, account);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Add New Account");

                    System.out.print("please enter account type[saving/checking]: ");
                    String type = scanner.nextLine().toLowerCase(Locale.ROOT);


                    System.out.print("please input account initial balance: ");
                    double initialBalance = scanner.nextInt();

                    try {
                        account = new Account(type, user.getNationalId(), user.getName(), initialBalance);
                        user.addAccount(account);
                        bank.addAccount(account);
                        System.out.println("Account added successfully!");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Logged out!");
                    break;
                default:
                    System.out.println("Invalid command");
            }
        } while (order != 3);
    }

    private static void handleUserAccountOrder(User user, Account account) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int order;
        do {
            showAccountPanel();
            order = getUserOrder();

            int amount;
            switch (order) {
                case 1:
                    System.out.print("please input withdrawal amount: ");
                    amount = scanner.nextInt();
                    try {
                        user.withdrawal(account, amount);
                        System.out.println("withdrawal completed!");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;


                case 2:
                    System.out.print("please enter deposit amount: ");
                    amount = scanner.nextInt();
                    try {
                        user.deposit(account, amount);
                        System.out.println("deposit completed!");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;


                case 3:
                    bank.displayAccounts();
                    Account destinationAccount = getUserAccountChoose();

                    System.out.print("please input amount: ");
                    amount = scanner.nextInt();

                    try {
                        user.transfer(account, destinationAccount, amount);
                        System.out.println("Transfer completed!");
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                    }
                    break;
                case 4:
                    user.checkBalance(account);
                    break;
            }
        } while (order != 5);

    }

    private static void showAccountPanel() {
        System.out.println("1. Withdrawal");
        System.out.println("2. Deposit");
        System.out.println("3. Transfer");
        System.out.println("4. Check balance");
        System.out.println("5. Back");
    }


    private static Account getUserAccountChoose() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("please enter account uuid: ");
        String serial = scanner.nextLine();

        return bank.findAccount(serial);
    }

    private static void showUserPanel() {
        System.out.println("User Panel");
        System.out.println("1. Existing Accounts");
        System.out.println("2. Add New Account");
        System.out.println("3. Logout");
    }

    private static void admin() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Admin Panel");

        System.out.print("Please enter admin username: ");
        String adminUsername = scanner.nextLine();

        System.out.print("Please enter admin password: ");
        String adminPassword = scanner.nextLine();

        if (!adminUsername.equals("sysadmin") || !adminPassword.equals("1234")) {
            System.out.println("Wrong username and password!");
            return;
        }

        int order;
        do {
            showAdminPanel();
            order = getUserOrder();
            switch (order) {
                case 1:
                    bank.displayUsers();
                    break;
                case 2:
                    bank.displayAccounts();
                    break;
                case 3:
                    System.out.print("Please enter user national id: ");
                    String nationalId = scanner.nextLine();

                    try {
                        bank.removeUser(nationalId);
                    } catch (Exception exception) {
                        System.out.println(exception.getMessage());
                        return;
                    }
                    break;
                case 4:
//                    no need to do anything just have to break the switch case so the loop can be finished
                    break;

                default:
                    System.out.println("Invalid Command!");
            }
        } while (order != 4);
    }

    private static void showAdminPanel() {
        System.out.println("Admin Panel");
        System.out.println("1. Display Users");
        System.out.println("2. Display Accounts");
        System.out.println("3. Remove User");
        System.out.println("4. Back to Main Menu (Logout)");
    }

    private static void signUp() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Sign Up");

        System.out.print("Please enter your first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Please enter you last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Please enter your national id: ");
        String nationalId = scanner.nextLine();

        System.out.print("Please enter your password : ");
        String password = scanner.nextLine();

        try {
            bank.register(firstName, lastName, nationalId, password);
            System.out.println("User created successfully!");
            System.out.println("Please login to have access to your newly created account.");
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static int getUserOrder() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("please input your order: ");
        return scanner.nextInt();
    }

    private static void showMainMenu() {
        System.out.println("Main Menu");
        System.out.println("1. Signup");
        System.out.println("2. Login");
        System.out.println("3. System Admin");
        System.out.println("4. Exit");
    }
}
