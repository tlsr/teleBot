package ua.com.cyberneophyte.bot.comands;

import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class CommandParser {
    public final String DELIMITER ="@";
    public final String BOT_NAME="test_latin_quotes_bot";


    public ParsedCommand parseCommand(String message){
        ParsedCommand parsedCommand = new ParsedCommand();
        if(!isCommand(message)){
            return parsedCommand;
        }else{
           parsedCommand = new ParsedCommand(getCommandFromMessage(message),getTextFromMessage(message));
        }

        return parsedCommand;
    }

    public Command getCommandFromMessage(String str){
        String message = str;
        if(isCommandHasText(message)){
            int firstSpace = message.indexOf(' ');
            System.out.println(firstSpace);
            message = message.substring(0,firstSpace);
        }
        System.out.println(message);
        if(isCommandHasBotName(message)){
            int delimiterIndex = message.indexOf(DELIMITER);
            message = message.substring(1,delimiterIndex);
        }else{
            message = message.substring(1);
        }
        return  Command.valueOf(message.toUpperCase(Locale.ROOT));
    }

    public String getTextFromMessage(String message){
        int firstSpace = message.indexOf(' ');
        message = message.substring(firstSpace+1);
        return message;
    }

    public boolean isCommand(String message){
        if(message.matches("/{1}[^/]+")){
            return true;
        }
        return false;
    }

    public boolean isCommandHasBotName(String message){
        String regexp = "(.+@"+BOT_NAME+" .*)|(.+@"+BOT_NAME+")";
        if(message.matches(regexp)){
            return true;
        }
        return false;
    }

    private boolean isCommandHasText(String message){
        String regexp = "(.+(@"+BOT_NAME+")? .*)";
        if(message.matches(regexp)){
            return true;
        }
        return false;
    }
}
