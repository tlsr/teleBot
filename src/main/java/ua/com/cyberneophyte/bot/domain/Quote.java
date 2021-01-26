package ua.com.cyberneophyte.bot.domain;


import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String quoteText;
    private String quoteTranslate;
    private String source;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setQuoteText(String quoteText) {
        this.quoteText = quoteText;
    }

    public void setQuoteTranslate(String quoteTranslate) {
        this.quoteTranslate = quoteTranslate;
    }


    public String getQuoteText() {
        return quoteText;
    }


    public String getQuoteTranslate() {
        return quoteTranslate;
    }

    @Override
    public String toString() {
        String quoteRepresentation = quoteText + "\n"+ quoteTranslate;
        if (!source.isEmpty()){
            quoteRepresentation = quoteRepresentation.concat("\n" + "Источник: " + source);
        }
        return quoteRepresentation;
    }
}
