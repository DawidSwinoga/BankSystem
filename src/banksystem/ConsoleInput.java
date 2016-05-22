package banksystem;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.math.BigDecimal;

public class ConsoleInput {

    private Scanner scanner;

    public ConsoleInput() {
        scanner = new Scanner(System.in);
    }

    public String nextString() {
        String str = null;

        try {
            str = scanner.next();
        } catch (Exception e) {
            System.out.println("Blad wczytania danych");
        } finally {
            clean();
        }
        return str;
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public int nextUnsignedInt() {
        int number = -1;

        do {
            try {
                number = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Enter the number.");
            } finally {
                clean();
            }
            if (number < 0) {
                System.out.println("The number must be bigger then zero.");
            }
        } while (number < 0);

        return number;
    }

    private void clean() {
        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }
    }
    
    public BigDecimal nextPositiveBigDecimal(){
        BigDecimal bigDecimal = null;
        boolean isCorrect ;
        
        do {
            isCorrect = true;
            try {
                bigDecimal = new BigDecimal(nextString());
            } catch (NumberFormatException e) {
                System.out.println("Podana dana musi byc liczba wieksza od zera.");
                isCorrect = false;
            }
            
        } while (!isCorrect || (bigDecimal.compareTo(BigDecimal.ZERO) != 1));
        return bigDecimal;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        scanner.close();
    }
}
