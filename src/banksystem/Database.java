package banksystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private static final long serialVersionUID = 1L;
    private int nextFreeClientID;
    private List<Account> accounts;
    private String name;

    public Database(String name) {
        this.nextFreeClientID = 0;
        this.accounts = new ArrayList<>();
        this.name = name;
    }
    
    

    public void add(String name, String lastName, Address address, String pesel) {
        accounts.add(new Account(nextFreeClientID, name, lastName, address, pesel));
        ++nextFreeClientID;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public Account remove(int clientNumber) {
        if (clientNumber >= nextFreeClientID) {
            return null;
        }
        
        for(int i = 0; i < accounts.size(); ++i) {
            if (accounts.get(i).getClientNumber() == clientNumber) {
                return accounts.remove(i);
            }
        }
        return null;
    }

    public Account findByClientNumber(int clientNumber) {
        for (Account account : accounts) {
            if (account.getClientNumber() == clientNumber) {
                return account;
            }
        }
        return null;
    }

    public Account findByPesel(String pesel) {
        for (Account account : accounts) {
            if (account.getPesel().equals(pesel)) {
                return account;
            }
        }
        return null;
    }

    public List<Account> findByName(String name) {
        List<Account> foundAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getName().equals(name)) {
                foundAccounts.add(account);
            }
        }
        return foundAccounts;
    }

    public List<Account> findByLastName(String lastName) {
        List<Account> foundAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getLastName().equals(lastName)) {
                foundAccounts.add(account);
            }
        }
        return foundAccounts;
    }

    public List<Account> findByAdress(Address address) {
        List<Account> foundAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getAdress().equals(address)) {
                foundAccounts.add(account);
            }
        }
        return foundAccounts;
    }
    
    public static Database load(String name) {
        Database database = null;
        FileInputStream fileInput = null;
        ObjectInputStream ois;
        
        try {
            fileInput = new FileInputStream(name + ".dat");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku.");
            return null;
        }
        
        try {
            ois = new ObjectInputStream(fileInput);
            database = (Database) ois.readObject();
        } catch (IOException e) {
            System.out.println("Blad deserializacji.");
        } catch (ClassNotFoundException e) {
            System.out.println("Nie znaleziono klasy.");
        }
        
        return database;
    }
    
    public void save() {
        try {
            FileOutputStream fileOutput = new FileOutputStream(name + ".dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutput);
            objectOutputStream.writeObject(this);
        } catch(Exception e) {
            System.out.println("Blad zapisu.");
        }
    }
}
