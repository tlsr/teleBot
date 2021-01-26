package ua.com.cyberneophyte.bot.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.com.cyberneophyte.bot.domain.Quote;

import java.util.Collection;

public interface QuoteRepo extends JpaRepository<Quote,Integer> {
    @Query(value = "SELECT id FROM quotes",nativeQuery = true)
    Collection<Integer> getAllIds();
    Quote findQuoteById(Integer id);

}
