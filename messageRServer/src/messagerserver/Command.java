package messagerserver;

public class Command {

    private String commandName;
    private String argument;

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandArgument() {
        return argument;
    }

    public void setCommandArgument(String commandArgument) {
        this.argument = commandArgument;
    }

    public Command(String commandName, String argument) {
        this.commandName = commandName;
        this.argument = argument;
    }
}
