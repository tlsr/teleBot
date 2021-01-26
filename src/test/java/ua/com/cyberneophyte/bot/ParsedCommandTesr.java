package ua.com.cyberneophyte.bot;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import ua.com.cyberneophyte.bot.comands.Command;
import ua.com.cyberneophyte.bot.comands.ParsedCommand;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource("/application-test.properties")
public class ParsedCommandTesr {

    @Test
    public void equalsReflexiveTest() throws Exception{
        ParsedCommand parsedCommand = new ParsedCommand(Command.START,"text");
        assertTrue(parsedCommand.equals(parsedCommand));
    }

    @Test
    public void equalsSymmetricTest() throws Exception{
        ParsedCommand parsedCommand1 = new ParsedCommand(Command.START,"text");
        ParsedCommand parsedCommand2 = new ParsedCommand(Command.START,"text");
        assertTrue(parsedCommand1.equals(parsedCommand2));
        assertTrue(parsedCommand2.equals(parsedCommand1));
    }

    @Test
    public void equalsTransitiveTest() throws Exception{
        ParsedCommand parsedCommand1 = new ParsedCommand(Command.START,"text");
        ParsedCommand parsedCommand2 = new ParsedCommand(Command.START,"text");
        ParsedCommand parsedCommand3 = new ParsedCommand(Command.START,"text");
        assertTrue(parsedCommand1.equals(parsedCommand2));
        assertTrue(parsedCommand2.equals(parsedCommand3));
        assertTrue(parsedCommand1.equals(parsedCommand3));
    }

    @RepeatedTest(10)
    @Test
    public void equalsRepeatedTest() throws Exception{
        ParsedCommand parsedCommand1 = new ParsedCommand(Command.START,"text");
        ParsedCommand parsedCommand2 = new ParsedCommand(Command.START,"text");
        assertTrue(parsedCommand1.equals(parsedCommand2));
        assertTrue(parsedCommand2.equals(parsedCommand1));
    }

    @Test
    public void notEqualsTest() throws Exception{

        ParsedCommand parsedCommand2 = new ParsedCommand(Command.START,"text");
        ParsedCommand parsedCommand1 = new ParsedCommand(Command.NONE,"text");
        assertFalse(parsedCommand1.equals(parsedCommand2));
        assertFalse(parsedCommand2.equals(parsedCommand1));
        parsedCommand1 = new ParsedCommand(Command.START,"another text");
        assertFalse(parsedCommand1.equals(parsedCommand2));
        assertFalse(parsedCommand2.equals(parsedCommand1));
    }
}
