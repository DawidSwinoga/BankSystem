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

    public void withdraw(BigDecimal amount) {
        if (isEnoughMoney(amount)) {
            balance = balance.subtract(amount);
        } else {
            System.out.println("Not enough money.");
        }
    }
    
    public void transfer(Account destinationAccount, BigDecimal amount) {
        if(isEnoughMoney(amount)) {
            withdraw(amount);
            destinationAccount.deposit(amount);
        } else {
             System.out.println("Not enough money.");
        }
    }

    public boolean isEnoughMoney(BigDecimal amount) {
        return balance.compareTo(BigDecimal.ZERO) >= 0;
    }

    public void displayInfo() {
        System.out.println(this.clientNumber + "\t" + this.lastName + "\t" + this.name + "\t" + this.adress + "\t" + this.pesel + "\t" + this.balance);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.lastName);
        hash = 11 * hash + Objects.hashCode(this.adress);
        hash = 11 * hash + Objects.hashCode(this.pesel);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Account other = (Account) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.adress, other.adress)) {
            return false;
        }
        if (!Objects.equals(this.pesel, other.pesel)) {
            return false;
        }
        return true;
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
