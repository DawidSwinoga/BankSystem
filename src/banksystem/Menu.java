package banksystem;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Database database;
    private Scanner scanner;

    public Menu(Database database) {
        scanner = new Scanner(System.in);
        this.database = database;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }

    public void display() {
        System.out.println("============= MENU ==========");
        System.out.println("1. Create new account.");
        System.out.println("2. Remove account");
        System.out.println("3. Deposit");
        System.out.println("4. Withdraw");
        System.out.println("5. Transfer");
        System.out.println("6. Display information about all accounts");
        System.out.println("7. Display information about selected accounts");
        System.out.println("0. Exit");
        System.out.println("\nOption: ");

        switch (parseAnswerToInt()) {
            case 1:
                createNewAccount();
                break;
            case 2:
                removeAccount();
                break;
            case 3:
                deposit();
                break;
            case 4:
                withdraw();
                break;
            case 5:
                transfer();
                break;
            case 6:
                displayAllAccounts();
                break;
            case 7:
                findByMenu();
                break;
            case 0:
                break;
            default:
                break;
        }
    }

    private void findByMenu() {
        System.out.println("Find by:");
        System.out.println("1. Client ID.");
        System.out.println("2. Name.");
        System.out.println("3. Last Name.");
        System.out.println("4. Pesel.");
        System.out.println("5. Adress.");
        System.out.println("0. Exit");

        switch (parseAnswerToInt()) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 0:
                break;
            default:
                break;
        }
    }
    
    private void displayAllAccounts() {
        if(database == null) {
            System.out.println("No accounts");
        } else {
            database.displayAllAccounts();
        }
    }
    

    private void transfer() {
        BigDecimal amount;
        Account accountSrc;
        Account accountDsc;
        System.out.println("==== transfer ====");

        System.out.println("Enter source client number");
        accountSrc = database.findByClientNumber(parseAnswerToInt());
        if (accountSrc != null) {
            System.out.println("Source account does not exist.");
            return;
        }

        System.out.println("Enter source client number");
        accountDsc = database.findByClientNumber(parseAnswerToInt());
        if (accountDsc != null) {
            System.out.println("Destinatnio account does not exist.");
            return;
        }

        System.out.println("How much money you want to transfer?");
        amount = new BigDecimal(parseAnswerToInt());

        if (accountSrc.isEnoughMoney(amount)) {
            if (confirm("withdraw money")) {
                accountSrc.withdraw(amount);
            }
        } else {
            System.out.println("Not enought money");
        }

    }

    private void withdraw() {
        BigDecimal amount;
        Account account;
        System.out.println("==== withdraw ====");
        System.out.println("Enter client number");
        account = database.findByClientNumber(parseAnswerToInt());

        System.out.println("How much money you want to withdraw?");
        amount = new BigDecimal(parseAnswerToInt());

        if (account != null) {
            if (account.isEnoughMoney(amount)) {
                if (confirm("withdraw money")) {
                    account.withdraw(amount);
                }
            }
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private void deposit() {
        BigDecimal amount;
        Account account;
        System.out.println("==== deposit ====");
        System.out.println("Enter client number");
        account = database.findByClientNumber(parseAnswerToInt());

        System.out.println("How much money you want to deposit?");
        amount = new BigDecimal(parseAnswerToInt());

        if (account != null) {
            if (confirm("deposit money")) {
                account.deposit(amount);
            }
        } else {
            System.out.println("Account does not exist.");
        }
    }

    private void removeAccount() {
        System.out.println("==== remove account ====");
        System.out.println("Enter client number: ");

        if (confirm("remove account")) {
            if (database.remove(parseAnswerToInt()) == null) {
                System.out.println("Account was not exist.");
            } else {
                System.out.println("Account was remove.");
            }
        }
    }

    private boolean confirm(String nameOption) {
        String choice;
        do {
            System.out.println("Are you sure you want to " + nameOption + "?[y/n]");
            choice = scanner.next();

            if (choice.equals("y") || choice.equals("Y")) {
                return true;
            }
            if (choice.equals("n") || choice.equals("N")) {
                return false;
            }
        } while (true);
    }

    private void createNewAccount() {
        String name;
        String lastName;
        String adress;
        String pesel;

        System.out.println("==== Create new account ====");
        System.out.println("Name: ");
        name = scanner.next();
        System.out.println("Last name: ");
        lastName = scanner.next();
        System.out.println("Adress: ");
        adress = scanner.next();
        System.out.println("Pesel: ");
        pesel = scanner.next();

        if (database.findByPesel(pesel) == null) {
            if (confirm("create new account")) {
                database.append(name, lastName, adress, pesel);
                System.out.println("Account was created.");
            } else {
                System.out.println("Account was not created.");
            }
        } else {
            System.out.println("Account already exists");
        }

    }

    private int parseAnswerToInt() {
        int answer = -1;

        do {
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter the number.");
            }
            if (answer < 0) {
                System.out.println("The number must be greater then zero.");
            }
        } while (answer < 0);
        return answer;
    }

}
