package tutorials.guice.part4.bookstore.impl;

import com.google.common.collect.Iterables;
import tutorials.guice.part4.bookstore.BookOrderService;
import tutorials.guice.part4.bookstore.Publisher;
import tutorials.guice.part4.bookstore.Title;

import javax.inject.Inject;
import java.util.Set;

public class PublisherImpl implements Publisher {

    private final String name;
    private final Set<Title> titles;
    private final BookOrderService orderService;

    @Inject
    public PublisherImpl(@PublisherName final String name, @PublisherTitles final Set<Title> titles,
                         final BookOrderService orderService) {
        this.name = name;
        this.titles = titles;
        this.orderService = orderService;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BookOrderService getOrderingService() {
        return orderService;
    }

    @Override
    public Iterable<Title> getTitles() {
        return titles;
    }

    @Override
    public Iterable<String> getAuthors() {
        return Iterables.transform(titles, TitleImpl.toAuthor());
    }

    @Override
    public String toString() {
        return new StringBuilder("publisher ").append(getName()).toString();
    }


}
