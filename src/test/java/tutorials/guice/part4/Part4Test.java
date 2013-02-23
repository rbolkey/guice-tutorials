package tutorials.guice.part4;

import com.google.common.collect.Lists;
import com.google.inject.ConfigurationException;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.junit.Test;
import tutorials.guice.BaseGuiceTest;
import tutorials.guice.part4.bookstore.BookOrderService;
import tutorials.guice.part4.bookstore.Publisher;
import tutorials.guice.part4.bookstore.Title;
import tutorials.guice.part4.bookstore.Topic;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Part4Test extends BaseGuiceTest {

    @Inject
    @SunnyDay
    private Publisher sunnyDay;

    @Inject
    @ConsciousKitten
    private Publisher consciousKitten;

    @Inject
    @Named("Self Help")
    private Topic selfHelp;

    @Override
    protected Iterable<? extends Module> getModules() {
        return Lists.newArrayList(
                new BookstoreModule(),
                new BookstoreModule.SunnyDayPublisherModule(),
                new BookstoreModule.ConsciousKittenPublisherModule());
    }

    @Test(expected = ConfigurationException.class)
    public void shouldNotExposePrivateBindings() {
        injector.getInstance(BookOrderService.class);
    }

    @Test
    public void shouldHaveTwoPublishers() {
        final Set<Publisher> publishers = injector.getInstance(Key.get(new TypeLiteral<Set<Publisher>>() {}));
        assertThat(publishers, hasSize(2));
    }

    @Test
    public void shouldHaveFourTopics() {
        final Set<Topic> topics = injector.getInstance(Key.get(new TypeLiteral<Set<Topic>>() {}));
        assertThat(topics, hasSize(4));
    }

    @Test
    public void shouldOrderBooksFromPublisher() {
        final Title sunnyDayTitle = sunnyDay.getTitles().iterator().next();
        assertThat(sunnyDay.getOrderingService().order(sunnyDayTitle), is(true));
    }

    @Test
    public void shouldNotOrderBooksFromWrongPublisher() {
        final Title sunnyDayTitle = sunnyDay.getTitles().iterator().next();
        assertThat(consciousKitten.getOrderingService().order(sunnyDayTitle), is(false));
    }

    @Test
    public void shouldHaveTwoSelfHelpBooks() {
        assertThat(selfHelp.getTitles(), hasSize(2));
    }
}
