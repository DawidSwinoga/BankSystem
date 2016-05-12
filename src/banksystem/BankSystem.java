package banksystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class BankSystem {
    private static Database database;
    
    public static void main(String[] args) {
        Menu menu;
        readDatabase("database");
        menu = new Menu(database);
        menu.display();
        writeDatabase("database");
        
    }
    
    public static void readDatabase(String nameFile) {
         try
      {
         FileInputStream fileIn = new FileInputStream(nameFile + ".ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         database = (Database)in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException e) {
          database = new Database();
      } catch(ClassNotFoundException e)
      {
         System.out.println("Database class not found");
         System.exit(0);
      }
    }
    
    
    public static void writeDatabase(String nameFile) {
        try
      {
         FileOutputStream fileOut =  new FileOutputStream(nameFile + ".ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(database);
         out.close();
         fileOut.close();
      }catch(IOException i)
      {
      }
    }
    
}
