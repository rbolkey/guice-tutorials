package tutorials.guice.part4.bookstore.impl;

import tutorials.guice.part4.bookstore.BookOrderService;
import tutorials.guice.part4.bookstore.Title;

import javax.inject.Inject;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookOrderServiceImpl implements BookOrderService {

    private final Logger logger;
    private final Set<Title> titles;

    @Inject
    BookOrderServiceImpl(final Logger logger, @PublisherTitles final Set<Title> titles) {
        this.logger = logger;
        this.titles = titles;
    }

    @Override
    public boolean order(final Title title) {
        if (titles.contains(title)) {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(new StringBuilder("Ordering ").append(title).append(" from ").append(title.getPublisher()).toString());
            }
            return true;
        } else {
            if (logger.isLoggable(Level.INFO)) {
                logger.info(new StringBuilder("Sorry! I'm not the publisher of ").append(title).toString());
            }
            return false;
        }
    }

}
