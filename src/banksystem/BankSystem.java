package banksystem;

public class BankSystem {

    public static void main(String[] args) {
        Database database;
        Menu menu;        
        database = Database.load("database");

        if (database == null) {
            database = new Database("database");
        }

        menu = new Menu(database);

        menu.display();
    }
}
