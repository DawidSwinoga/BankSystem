package banksystem;

import java.io.Serializable;
import java.math.BigDecimal;




public class ClientAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    private int clientNumber;
    private String name;
    private String lastName;
    private String adress;
    private String pesel;
    private BigDecimal balance;

    public ClientAccount(int clientNumber, String name, String lastName, String adress, String pesel, BigDecimal balance) {
        this.clientNumber = clientNumber;
        this.name = name;
        this.lastName = lastName;
        this.adress = adress;
        this.pesel = pesel;
        this.balance = balance;
    }
    
    public void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
    
    public void withdraw(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
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
