package banksystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BankSystem {

    
    public static void main(String[] args) {
        Database database = new Database();
          try
      {
         FileInputStream fileIn = new FileInputStream("account.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         database = (Database)in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
        
        database.displayAllAccounts();
        Menu menu = new Menu();
        System.out.println("Welcome to the banking system!");
        menu.display();
        
        database.append("Dawid", "Swinoga", "Bedzelin ul. Grzybowa 14", "95040265562");
        database.append("Michal", "Parysz", "Bedzelin ul. Grzybowa 14", "95050403652");
        database.displayAllAccounts();
        System.out.println("find by:");
        database.findByClientNumber(1).displayInfo();
        System.out.println("End of findy by");
        database.remove(0);
        database.displayAllAccounts();
        database.append("Piotr", "Borczyk", "Belkow", "Nie wiem");
        database.displayAllAccounts();

        try
      {
         FileOutputStream fileOut =
         new FileOutputStream("account.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(database);
         out.close();
         fileOut.close();
      }catch(IOException i)
      {
      }
        
        
        try
      {
         FileInputStream fileIn = new FileInputStream("account.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         database = (Database)in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i)
      {
         i.printStackTrace();
         return;
      }catch(ClassNotFoundException c)
      {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return;
      }
        
        database.displayAllAccounts();
    }
    
}
