package banksystem;

import java.util.InputMismatchException;
import java.util.Scanner;


public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }
    
    public String getString() {  
        return scanner.next();
    }  
    
     public int getUnsignedInt() {
        int number = -1;        
        
        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter the number.");
                scanner.nextLine();
            }
            if (number < 0) {
                System.out.println("The number must be greater then zero.");
            }
        } while (number < 0);
        
        return number;
        
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }    
}
