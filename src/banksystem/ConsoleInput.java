package banksystem;

import java.util.Scanner;


public class ConsoleInput {
    private Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }
    
    

    
}
