package tutorials.guice.part4.bookstore.impl;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.assistedinject.AssistedInject;
import tutorials.guice.part4.bookstore.Publisher;
import tutorials.guice.part4.bookstore.Topic;
import tutorials.guice.part4.bookstore.Title;

import javax.annotation.Nullable;

public class TitleImpl implements Title {

    private final String name;
    private final Publisher publisher;
    private final String author;
    private final Optional<Topic> topicOptional;

    private TitleImpl(final String name, final Publisher publisher, final String author,
                     final Optional<Topic> topicOptional) {
        this.name = name;
        this.publisher = publisher;
        this.author = author;
        this.topicOptional = topicOptional;
    }

    @AssistedInject
    TitleImpl(@Assisted("name") final String name, final Publisher publisher, @Assisted("author") final String author) {
        this(name, publisher, author, Optional.<Topic>absent());
    }

    @AssistedInject
    TitleImpl(@Assisted("name") final String name, final Publisher publisher, @Assisted("author") final String author,
              @Assisted @Nullable final Topic topic) {
        this(name, publisher, author, Optional.fromNullable(topic));
    }

    static Function<Title, String> toAuthor() {
        return new Function<Title, String>() {
            @Nullable
            @Override
            public String apply(@Nullable final Title input) {
                return input != null ? input.getAuthor() : null;
            }
        };
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public Optional<Topic> getTopic() {
        return topicOptional;
    }

    @Override
    public String toString() {
        return new StringBuilder("book title ").append(name).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TitleImpl title = (TitleImpl) o;

        if (!author.equals(title.author)) return false;
        if (!name.equals(title.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + author.hashCode();
        return result;
    }
}
