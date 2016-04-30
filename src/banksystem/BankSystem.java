package banksystem;

import java.math.BigDecimal;

public class BankSystem {

    
    public static void main(String[] args) {
        System.out.println("Test");
        ClientAccount test = new ClientAccount(123, "Dawid", "Swinoga", "Bedzelin ul. Grzybowa 14", "95050403652", new BigDecimal(1200));
        test.displayInfo();
        test.withdraw(new BigDecimal(100));
        test.displayInfo();
        test.deposit(new BigDecimal(500));
        test.displayInfo();
    }
    
}
