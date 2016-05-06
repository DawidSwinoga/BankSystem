package banksystem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Database implements Serializable {

    private static final long serialVersionUID = 1L;
    private int nextFreeClientID;
    private List<Account> accounts;

    public Database() {
        this.nextFreeClientID = 0;
        this.accounts = new ArrayList<>();
    }

    public void append(String name, String lastName, String adress, String pesel) {
        accounts.add(new Account(nextFreeClientID, name, lastName, adress, pesel));
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

    public List<Account> findByAdress(String adress) {
        List<Account> foundAccounts = new ArrayList<>();

        for (Account account : accounts) {
            if (account.getAdress().equals(adress)) {
                foundAccounts.add(account);
            }
        }
        return foundAccounts;
    }
}
