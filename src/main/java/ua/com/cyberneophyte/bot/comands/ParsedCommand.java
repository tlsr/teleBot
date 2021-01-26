package ua.com.cyberneophyte.bot.comands;


import java.util.Objects;

public class ParsedCommand {
    private Command command;
    private String commandText;

    public ParsedCommand(Command command, String commandText) {
        this.command = command;
        this.commandText = commandText;
    }

    public ParsedCommand() {
        this.command = Command.NONE;
        this.commandText = "";
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsedCommand that = (ParsedCommand) o;
        return command == that.command && commandText.equals(that.commandText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, commandText);
    }
}
