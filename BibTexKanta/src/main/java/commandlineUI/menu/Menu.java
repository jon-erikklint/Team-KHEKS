package commandlineUI.menu;

import commandlineUI.Command;
import io.IO;
import java.util.HashMap;
import java.util.Map;
import reference.Reference;

public abstract class Menu implements Command {

    private Map<String, Command> commands;
    private String[] askPrompts;
    private String[] startLines;
    private Command defaultCommand;

    protected IO io;
    
    private Reference givenRef;

    public Menu(IO io, String[] startLines, String[] askPrompts) {
        this.io = io;
        this.askPrompts = askPrompts;
        this.startLines = startLines;

        commands = new HashMap<>();
    }

    protected Map<String, Command> getCommands() {
        return commands;
    }

    protected void setDefaultCommand(Command command) {
        this.defaultCommand = command;
    }

    protected Reference referenceToGiveToCommands() {
        return givenRef;
    }

    @Override
    public boolean execute(Reference ref) {
        givenRef = ref; //Tarvitaan että voidaan antaa mahdollisuus laittaa eteenpäin
        //menulle annettu reference

        beforeLoop();

        while (true) {
            io.printLines(askPrompts);
            String line = io.readLine("").toLowerCase();

            if (!actOnLine(line)) {
                break;
            }
        }

        return true;
    }
    
    public boolean actOnLine(String line){
        Command toExecute = commands.getOrDefault(line, defaultCommand);

        return toExecute.execute(referenceToGiveToCommands());
    }
    
    public void beforeLoop(){
        io.printLines(startLines);
    }

}
