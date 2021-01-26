package ua.com.cyberneophyte.bot.comands;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CommandParser {
    public final String DELIMITER ="@";
    public final String BOT_NAME="test_latin_quotes_bot";


    public ParsedCommand parseCommand(String message){
        ParsedCommand parsedCommand = new ParsedCommand();
        if(!isCommand(message) && !isCommandForMe(message)){
            return parsedCommand;
        }else{
            String commandPart = "";
            String textPart = "";
            if(isCommandHasText(message)){
                int firstSpace = message.indexOf(' ');
                commandPart = message.substring(0,firstSpace);
                textPart = message.substring(firstSpace+1);
            }else{
                commandPart = message;
            }
            int delimiterIndex = commandPart.indexOf(DELIMITER);
            commandPart = commandPart.substring(1,delimiterIndex);
            parsedCommand = new ParsedCommand(Command.valueOf(commandPart.toUpperCase(Locale.ROOT)),textPart);
        }

        return parsedCommand;
    }

    public boolean isCommand(String message){
        if(message.matches("/{1}[^/]+")){
            return true;
        }
        return false;
    }

    public boolean isCommandForMe(String message){
        String regexp = "(.+@"+BOT_NAME+" .*)|(.+@"+BOT_NAME+")";
        if(message.matches(regexp)){
            return true;
        }
        return false;
    }

    private boolean isCommandHasText(String message){
        String regexp = "(.+@"+BOT_NAME+" .*)";
        if(message.matches(regexp)){
            return true;
        }
        return false;
    }
}
