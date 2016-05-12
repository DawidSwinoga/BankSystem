package banksystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleInput {
    private Scanner scanner;

    
    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }
    
    public String getString() {
        cleanInput();
        return scanner.next();
    }  
    
     public int getUnsignedInt() {
        int number = -1;        
        
        do {
            cleanInput();   
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter the number.");                             
            }
            if (number < 0) {
                System.out.println("The number must be greater then zero.");
            }
        } while (number < 0);
        
        return number;
        
    }
     
     private void cleanInput() {
         if (scanner.hasNextLine()) {
             scanner.nextLine();
         }
     }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }    
}
