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
        do {
            System.out.println("============= MENU ==========");
            System.out.println("1. Stworz nowe konto.");
            System.out.println("2. Usun konto.");
            System.out.println("3. Wplata.");
            System.out.println("4. Wyplata.");
            System.out.println("5. Przelew");
            System.out.println("6. Wyswietl informacje o wszystkich kontach");
            System.out.println("7. Wyswietl informacje o wybranych kontach");
            System.out.println("0. Wyjdz");
            System.out.println("\nOpcja: ");

            switch (consoleInput.nextUnsignedInt()) {
                case 1:
                    createAccount();
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
                    database.save();
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    private void findByMenu() {
        boolean exit = false;
        do {
            System.out.println("Szukaj po:");
            System.out.println("1. Numerze klienta");
            System.out.println("2. Imieniu.");
            System.out.println("3. Nazwisku.");
            System.out.println("4. Peslu.");
            System.out.println("5. Adresie.");
            System.out.println("0. Wyjscie");

            switch (consoleInput.nextUnsignedInt()) {
                case 1:
                    System.out.println("=== Szukaj po numerze ===");
                    System.out.print("numer klienta: ");
                    printAccount(database.findByClientNumber(consoleInput.nextUnsignedInt()));
                    break;
                case 2:
                    System.out.println("=== Szukaj po imieniu ===");
                    System.out.print("imie: ");
                    printAccounts(database.findByName(consoleInput.nextString()));
                    break;
                case 3:
                    System.out.println("=== Szukaj po nazwisku ===");
                    System.out.print("nazwisko: ");
                    printAccounts(database.findByLastName(consoleInput.nextString()));
                    break;
                case 4:
                    System.out.println("=== Szukaj po peslu ===");
                    System.out.print("pesel: ");
                    printAccount(database.findByPesel(consoleInput.nextString()));
                    break;
                case 5:
                    System.out.println("=== Szukaj po adresie ===");
                    printAccounts(database.findByAdress(newAddress()));
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
                System.out.println("\n\n");
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
        System.out.println("==== przelew ====");

        System.out.println("Podaj numer klienta zrodlowego");
        accountSrc = database.findByClientNumber(consoleInput.nextUnsignedInt());
        if (accountSrc == null) {
            System.out.println("Konto zrodlowe nie istnieje");
            return;
        }

        System.out.println("Podaj numer konta docelowego");
        accountDsc = database.findByClientNumber(consoleInput.nextUnsignedInt());
        if (accountDsc == null) {
            System.out.println("Konto docelowe nie istnieje.");
            return;
        }

        System.out.println("Podaj ilosc pieniedzy ktore chcesz przelac?");
        amount = consoleInput.nextPositiveBigDecimal();

        if (accountSrc.isEnoughMoney(amount)) {
            if (confirm("Czy na pewno chcesz wykonac przelew?")) {
                accountSrc.withdraw(amount);
                accountDsc.deposit(amount);
            }
        } else {
            System.out.println("Nie wystarczajaca liczba pieniedzy.");
        }

    }

    private void withdraw() {
        BigDecimal amount;
        Account account;
        System.out.println("==== Wyplata ====");
        System.out.println("Wprowadz numer klienta.");
        account = database.findByClientNumber(consoleInput.nextUnsignedInt());

        System.out.println("Ile pieniedzy chcesz wyplacic?");
        amount = consoleInput.nextPositiveBigDecimal();

        if (account == null) {
            System.out.println("Brak konta o podanym numerze klienta.");
            return;
        }

        if (account.isEnoughMoney(amount)) {
            if (confirm("Czy na pewno chcesz wyplacic pieniadze?")) {
                account.withdraw(amount);
            }
        }

    }

    private void deposit() {
        BigDecimal amount;
        Account account;
        System.out.println("==== Wplata ====");
        System.out.println("Wprowadz nr klienta");
        account = database.findByClientNumber(consoleInput.nextUnsignedInt());

        System.out.println("Ile pieniedzy chcesz wplacic?");
        amount = consoleInput.nextPositiveBigDecimal();

        if (account == null) {
            System.out.println("Konto o podanym numerze klienta nie istnieje.");
            return;
        }

        if (confirm("Czy na pewno chcesz wplacic pieniadze?")) {
            account.deposit(amount);
        }

    }

    private void removeAccount() {
        System.out.println("==== Usuwanie konta ====");
        System.out.println("Podaj numer klienta: ");
        int clientNumber = consoleInput.nextUnsignedInt();

        if (confirm("Czy na pewno chcesz usunac konto?")) {
            if (database.remove(clientNumber) == null) {
                System.out.println("Konto nie istnieje.");
            } else {
                System.out.println("Konto zostalo usuniete.");
            }
        }
    }

    private boolean confirm(String operationName) {
        String choice;
        do {
            System.out.println(operationName + "[y/n]");
            choice = consoleInput.nextString();

            if (choice.equals("y") || choice.equals("Y")) {
                return true;
            }
            if (choice.equals("n") || choice.equals("N")) {
                return false;
            }
        } while (true);
    }

    private void createAccount() {
        String name, lastName, pesel;
        Address address;

        System.out.println("==== Tworzenie nowego konta ====");
        System.out.print("Imie: ");
        name = consoleInput.nextString();
        System.out.print("Nazwisko: ");
        lastName = consoleInput.nextString();

        address = newAddress();
        System.out.print("Pesel: ");
        pesel = consoleInput.nextString();

        if (database.findByPesel(pesel) != null) {
            System.out.println("Konto o podanym numerze pesel juz istnieje.");
            return;
        }
        if (confirm("Czy chcesz stworzyc nowe konto?")) {
            database.add(name, lastName, address, pesel);
            System.out.println("Konto zostalo stworzone.");
        } else {
            System.out.println("Konto nie zostalo stworzone.");
        }
    }

    private Address newAddress() {
        String city, street, postalCode;
        System.out.print("Miasto: ");
        city = consoleInput.nextLine();
        System.out.print("Ulica: ");
        street = consoleInput.nextLine();
        System.out.print("Kod pocztowy: ");
        postalCode = consoleInput.nextString();

        return new Address(city, street, postalCode);
    }
}
