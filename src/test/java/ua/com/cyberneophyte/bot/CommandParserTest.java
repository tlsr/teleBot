package ua.com.cyberneophyte.bot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ua.com.cyberneophyte.bot.comands.Command;
import ua.com.cyberneophyte.bot.comands.CommandParser;
import ua.com.cyberneophyte.bot.comands.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class CommandParserTest {

    @Autowired
    CommandParser commandParser;


    @ParameterizedTest
    @ValueSource(strings = {"/start","/help","/rq"})
    public void anyCorrectCommand(String command) throws Exception{
        assertTrue(commandParser.isCommand(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"start","help","rq","//","\\","|"})
    public void anyInCorrectCommand(String command) throws Exception{
        assertFalse(commandParser.isCommand(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/start@test_latin_quotes_bot","/start@test_latin_quotes_bot dsadsadas"})
    public void isCommandForMe(String command) throws Exception{
        assertTrue(commandParser.isCommandHasBotName(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/start@another_bot","/start@test_latin_quotes_botdsadsadas","/start@tanother_bot dsadsadas"})
    public void isCommandNotForMe(String command) throws Exception{
        assertFalse(commandParser.isCommandHasBotName(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/start@test_latin_quotes_bot text","/start text"})
    public void parseCommand(String command ) throws Exception{
        ParsedCommand actualParsedCommand = commandParser.parseCommand(command);
        ParsedCommand expectedParsedCommand = new ParsedCommand(Command.START,"text");
        assertEquals(expectedParsedCommand,actualParsedCommand);
    }

    @ParameterizedTest
    @ValueSource(strings = {"/start@test_latin_quotes_bot","/start@test_latin_quotes_bot dsadsadas","/start"})
    public void getCommandFromMessage(String message){
        assertEquals(Command.START,commandParser.getCommandFromMessage(message));
    }
    @ParameterizedTest
    @ValueSource(strings = {"/start@test_latin_quotes_bot text","/start text"})
    public void getTextFromMessage(String message){
        assertEquals("text",commandParser.getTextFromMessage(message));
    }


}
