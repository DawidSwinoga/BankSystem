package banksystem;

import java.math.BigDecimal;
import java.util.List;

public class Menu {

    private Database database;
    private ConsoleInput consoleInput;

    public Menu(Database database) {
        this.database = database;
        consoleInput = new ConsoleInput();
    }

    public void display() {
        boolean exit = false;

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

        do {
            switch (consoleInput.getUnsignedInt()) {
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
                    printAccounts(database.getAccounts());
                    break;
                case 7:
                    findByMenu();
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private void findByMenu() {
        boolean exit = false;
        System.out.println("Find by:");
        System.out.println("1. Client number.");
        System.out.println("2. Name.");
        System.out.println("3. Last Name.");
        System.out.println("4. Pesel.");
        System.out.println("5. Adress.");
        System.out.println("0. Exit");

        do {
            switch (consoleInput.getUnsignedInt()) {
                case 1:
                    System.out.println("=== Find by client number ===");
                    System.out.println("client number: ");
                    printAccount(database.findByClientNumber(consoleInput.getUnsignedInt()));
                    break;
                case 2:
                    System.out.println("=== Find by name ===");
                    System.out.println("name: ");
                    printAccounts(database.findByName(consoleInput.getString()));
                    break;
                case 3:
                    System.out.println("=== Find by last name ===");
                    System.out.println("last name: ");
                    printAccounts(database.findByLastName(consoleInput.getString()));
                    break;
                case 4:
                    System.out.println("=== Find by pesel ===");
                    System.out.println("pesel: ");
                    printAccount(database.findByPesel(consoleInput.getString()));
                    break;
                case 5:
                    System.out.println("=== Find by adress ===");
                    System.out.println("adress: ");
                    printAccounts(database.findByAdress(consoleInput.getString()));
                    break;
                case 0:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private void printAccounts(List<Account> accounts) {
        if (database == null) {
            System.out.println("No accounts");
        } else {
            for (Account account : accounts) {
                account.displayInfo();
            }
        }
    }

    private void printAccount(Account account) {
        if (account == null) {
            System.out.println("No account");
        } else {
            account.displayInfo();
        }
    }

    private void transfer() {
        BigDecimal amount;
        Account accountSrc;
        Account accountDsc;
        System.out.println("==== transfer ====");

        System.out.println("Enter source client number");
        accountSrc = database.findByClientNumber(consoleInput.getUnsignedInt());
        if (accountSrc != null) {
            System.out.println("Source account does not exist.");
            return;
        }

        System.out.println("Enter source client number");
        accountDsc = database.findByClientNumber(consoleInput.getUnsignedInt());
        if (accountDsc != null) {
            System.out.println("Destinatnio account does not exist.");
            return;
        }

        System.out.println("How much money you want to transfer?");
        amount = new BigDecimal(consoleInput.getString());

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
        account = database.findByClientNumber(consoleInput.getUnsignedInt());

        System.out.println("How much money you want to withdraw?");
        amount = new BigDecimal(consoleInput.getString());

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
        account = database.findByClientNumber(consoleInput.getUnsignedInt());

        System.out.println("How much money you want to deposit?");
        amount = new BigDecimal(consoleInput.getUnsignedInt());

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
            if (database.remove(consoleInput.getUnsignedInt()) == null) {
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
            choice = consoleInput.getString();

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
        name = consoleInput.getString();
        System.out.println("Last name: ");
        lastName = consoleInput.getString();
        System.out.println("Adress: ");
        adress = consoleInput.getString();
        System.out.println("Pesel: ");
        pesel = consoleInput.getString();

        if (database.findByPesel(pesel) == null) {
            if (confirm("create new account")) {
                database.add(name, lastName, adress, pesel);
                System.out.println("Account was created.");
            } else {
                System.out.println("Account was not created.");
            }
        } else {
            System.out.println("Account already exists");
        }

    }
}
