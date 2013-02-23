package tutorials.guice.part4.bookstore;

import com.google.inject.assistedinject.Assisted;

public interface TitleFactory {

    /**
     * Note: Assisted annotation only necessary because multiple paramters share the same type.
     */
    public Title create(@Assisted("name") String name, @Assisted("author") String author);

    public Title create(@Assisted("name") String name, @Assisted("author") String author, Topic topic);
}
