package ua.com.cyberneophyte.bot.core;


/*import org.apache.log4j.Logger;*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ua.com.cyberneophyte.bot.domain.Quote;
import ua.com.cyberneophyte.bot.service.QuoteService;

import javax.transaction.Transactional;

@Component
@Scope("prototype")
public class Bot extends TelegramLongPollingBot {
    private static final Logger log = LoggerFactory.getLogger(TelegramLongPollingBot.class);

    @Autowired
    private QuoteService quoteService;

    final int RECONNECT_PAUSE = 10000;

    @Value("${bot.name}")
    private String botName;

    @Value("${bot.token}")
    private String botToken;


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    @Transactional
    public void onUpdateReceived(Update update) {
        log.debug("new Update recieve");
        if (update.hasMessage() && update.getMessage().hasText()) {
                    Quote quote = quoteService
                    .getRandomQuote();
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
            message.setChatId(update.getMessage().getChatId().toString());
            message.setText(quote.toString());
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                log.error("Error happend {}", e);
            }
        }

    }


}
