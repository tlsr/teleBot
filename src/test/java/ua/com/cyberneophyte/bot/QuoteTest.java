package ua.com.cyberneophyte.bot;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import ua.com.cyberneophyte.bot.domain.Quote;
import ua.com.cyberneophyte.bot.repos.QuoteRepo;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
public class QuoteTest {

    @Autowired
    QuoteRepo quoteRepo;

    @Test
    @Sql(value = {"/create-quote-before.sql",},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-all-from-db.sql",}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    @Ignore
    public void quoteWithoutSourceToStringTest() throws Exception{
        Quote quote = quoteRepo.findQuoteById(1);
        String str = quote.toString();
        assertTrue(str.contains("\n"));
        assertTrue(str.contains("A Deo rex, a rege lex."));
        assertTrue(str.contains("От Бога король, от короля закон."));
    }

    @Ignore
    @Test
    @Sql(value = {"/create-quote-before.sql",},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/delete-all-from-db.sql",}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void quoteWithSourceToStringTest() throws Exception{
        Quote quote = quoteRepo.findQuoteById(3);
        String str = quote.toString();
        assertFalse(quote.getSource().isEmpty());
        assertTrue(str.matches(".+\n.+\n.+"));// regexp represents string with 2 \n
        assertTrue(str.contains("Ab altero expectes, alteri quod feceris."));
        assertTrue(str.contains("Жди от другого того, что сам ты сделал другому."));
        assertTrue(str.contains("Источник: Публилий Сир"));
    }
}
