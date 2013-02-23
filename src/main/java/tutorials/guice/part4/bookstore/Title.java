package tutorials.guice.part4.bookstore;

import com.google.common.base.Optional;

public interface Title {

    String getName();

    Publisher getPublisher();

    String getAuthor();

    Optional<Topic> getTopic();

}
