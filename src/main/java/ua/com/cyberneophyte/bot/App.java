package ua.com.cyberneophyte.bot;


/*import org.apache.log4j.Logger;*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ua.com.cyberneophyte.bot.core.Bot;
import ua.com.cyberneophyte.bot.domain.Quote;
import ua.com.cyberneophyte.bot.parser.Parser;
import ua.com.cyberneophyte.bot.repos.QuoteRepo;
import ua.com.cyberneophyte.bot.service.QuoteService;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {
    @Autowired
    private Parser parser;
    @Autowired
    private QuoteRepo quoteRepo;
    @Autowired
    private QuoteService quoteService;
    @Autowired
    private Bot bot;
    /*   private static final Logger log = Logger.getLogger(App.class);*/


    public static void main(String[] args) {

        SpringApplication.run(App.class, args);


    }

    @Override
    public void run(String... args) throws Exception {
        // parser.fillDbWithQuotes();
        //System.out.println(quoteRepo.getAllIds());
        /*List<Quote> list = parser.parse();
        for (Quote q : list) {
            if (!q.getSource().isEmpty()) {
                System.out.println(q);
            }
        }
        Quote quote = quoteService.getRandomQuote();
        System.out.println(quote.getQuoteText() + "\n" + quote.getQuoteTranslate());*/
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}