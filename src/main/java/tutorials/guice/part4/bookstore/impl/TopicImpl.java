package tutorials.guice.part4.bookstore.impl;

import com.google.common.collect.Sets;
import com.google.inject.assistedinject.Assisted;
import tutorials.guice.part4.bookstore.Title;
import tutorials.guice.part4.bookstore.Topic;

import javax.inject.Inject;
import java.util.Set;

public class TopicImpl implements Topic {

    private final String name;
    private final Set<Title> titles;

    @Inject
    public TopicImpl(@Assisted final String name) {
        this.name = name;
        this.titles = Sets.newHashSet();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Set<Title> getTitles() {
        return titles;
    }

    @Override
    public void addTitle(final Title title) {
        this.titles.add(title);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final TopicImpl topic = (TopicImpl) o;

        if (!name.equals(topic.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return new StringBuilder("topic ").append(name).toString();
    }
}
