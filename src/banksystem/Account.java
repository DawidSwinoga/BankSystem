package banksystem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;
    private int clientNumber;
    private String name;
    private String lastName;
    private String adress;
    private String pesel;
    private BigDecimal balance;

    public Account(int clientNumber, String name, String lastName, String adress, String pesel) {
        this.clientNumber = clientNumber;
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.pesel = pesel;
        this.balance = BigDecimal.ZERO;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public boolean withdraw(BigDecimal amount) {
        if (isEnoughMoney(amount)) {
            balance = balance.subtract(amount);
            return true;
        } else {
            return false;
        }
    }
    
    public boolean transfer(Account destinationAccount, BigDecimal amount) {
        if(isEnoughMoney(amount)) {
            withdraw(amount);
            destinationAccount.deposit(amount);
            return true;
        } else {
             return false;
        }
    }

    public boolean isEnoughMoney(BigDecimal amount) {
        return balance.subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
    }

    public void displayInfo() {
        System.out.println(this.clientNumber + "\t" + this.lastName + "\t" + this.name + "\t" + this.adress + "\t" + this.pesel + "\t" + this.balance);
    }


    public int getClientNumber() {
        return clientNumber;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAdress() {
        return adress;
    }

    public String getPesel() {
        return pesel;
    }

    public BigDecimal getBalance() {
        return balance;
    }

}
