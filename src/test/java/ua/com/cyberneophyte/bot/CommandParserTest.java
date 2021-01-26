package ua.com.cyberneophyte.bot;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
        assertTrue(commandParser.isCommandForMe(command));
    }

    @ParameterizedTest
    @ValueSource(strings = {"/start@another_bot","/start@test_latin_quotes_botdsadsadas","/start@tanother_bot dsadsadas"})
    public void isCommandNotForMe(String command) throws Exception{
        assertFalse(commandParser.isCommandForMe(command));
    }

    @Test
    public void parseCommand() throws Exception{
        String command ="/start";
        ParsedCommand actualParsedCommand = commandParser.parseCommand(command);
        ParsedCommand expectedParsedCommand = new ParsedCommand(Command.START,"");
        assertEquals(expectedParsedCommand,actualParsedCommand);
    }
}
