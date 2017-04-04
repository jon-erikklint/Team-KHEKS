package commandlineUI;

import database.Database;
import io.IO;
import io.ConsoleIO;

public class MainMenu {
    
    private final IO io;
    private final Database db;
    private final String[] options = new String[3];
    
    public MainMenu(Database db) {
        this.io = new ConsoleIO();
        this.db = db;
        this.options[0] = "Päävalikko:\n";
        this.options[1] = "1 Lisää kirja viiteluetteloon";
        this.options[2] = "Q Lopeta ohjelma";
    }
    
    public void loop() {
        String command;
        boolean again = true;
        
        while (again) {
            
            listOptions();
            command = io.readLine("\nValitse toiminto");
            
            switch (command) {
                case "1":
                    BookAdder newbook = new BookAdder(db, io);
                    newbook.addBookToDB();
                    break;
                    
                case "q":
                    again = false;
                    break;
                
                case "Q":
                    again = false;
                    break;
            }
        }
    }
    
    public void openMainMenu() {

        io.print("\nLähdeviitehallintaohjelma by Team-KHEKS.");
        io.print("\nTERVETULOA!");
        loop();
    }
    
    private void listOptions() {
        io.print("");
        for (int a = 0; a < this.options.length; a++) {
            io.print(options[a]);
        }        
    }
}