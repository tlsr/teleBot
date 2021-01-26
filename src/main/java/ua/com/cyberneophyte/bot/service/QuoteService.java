package ua.com.cyberneophyte.bot.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ua.com.cyberneophyte.bot.domain.Quote;
import ua.com.cyberneophyte.bot.repos.QuoteRepo;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Optional;

@Service
@Transactional
public class QuoteService {
    private final QuoteRepo quoteRepo;

    public QuoteService(QuoteRepo quoteRepo){
        this.quoteRepo =quoteRepo;
    }
    @Transactional
    public Quote getRandomQuote(){
        ///Collection<Integer> ids =  quoteRepo.getAllIds();
       // Quote quote= quoteRepo.getOne(ids.stream().findAny().orElseThrow(()->new NullPointerException()));
        int totalAmount = (int) quoteRepo.count();
        SecureRandom sr = new SecureRandom();
        int id = sr.nextInt(totalAmount)+1;

        Quote quote = quoteRepo.findQuoteById(id);

        return quote;
    }
}
