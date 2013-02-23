package tutorials.guice.part4.bookstore;

import java.util.Set;

public interface Topic {

    String getName();

    Set<Title> getTitles();

    void addTitle(Title title);
}
