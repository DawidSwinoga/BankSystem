package banksystem;

public class Menu {
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
    }
    
    private void findByMenu() {
        System.out.println("Find by:");
        System.out.println("1. Client ID.");
        System.out.println("2. Name.");
        System.out.println("3. Last Name.");
        System.out.println("4. Pesel.");
        System.out.println("5. Adress.");
        System.out.println("0. Exit");
    }

}
