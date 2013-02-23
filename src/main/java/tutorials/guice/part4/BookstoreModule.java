package tutorials.guice.part4;

import com.google.common.collect.ImmutableSet;
import com.google.inject.*;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.matcher.Matchers;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import tutorials.guice.part4.bookstore.*;
import tutorials.guice.part4.bookstore.impl.*;

import javax.inject.Named;
import java.util.Set;

public class BookstoreModule extends AbstractModule {

    private Multibinder<Topic> topicBinder;
    private Provider<TopicFactory> topicFactory;

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder().implement(Topic.class, TopicImpl.class).build(TopicFactory.class));
        topicFactory = getProvider(TopicFactory.class);
        topicBinder = Multibinder.newSetBinder(binder(), Topic.class);

        addTopic("Self Help");
        addTopic("Current Events");
        addTopic("Mystery");
        addTopic("Science");
    }

    private TopicFactory topicFactory() {
        return topicFactory.get();
    }

    void addTopic(final String name) {
        bind(Topic.class).annotatedWith(Names.named(name)).toProvider(new Provider<Topic>() {
            @Override
            public Topic get() {
                return topicFactory().create(name);
            }
        }).in(Singleton.class);
        topicBinder.addBinding().to(Key.get(Topic.class, Names.named(name)));
    }

    public static class CommonPrivatePublisherModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new FactoryModuleBuilder().implement(Title.class, TitleImpl.class).build(TitleFactory.class));
            bind(BookOrderService.class).to(BookOrderServiceImpl.class);
            bind(Publisher.class).to(PublisherImpl.class);
            // bind a type listener that matches any type (we could make this more restrictive to just Title instances)
            bindListener(Matchers.any(), new TitleTypeListener());
            // tell the injector that this type is required (given lazy creation, this is important for necessary
            // dependencies supplied by other modules).
            requireBinding(Key.get(new TypeLiteral<Set<Title>>() {}, PublisherTitles.class));
            requireBinding(Key.get(String.class, PublisherName.class));
        }
    }

    public static class ConsciousKittenPublisherModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new PrivateModule() {
                @Override
                protected void configure() {
                    install(new CommonPrivatePublisherModule());
                    bindConstant().annotatedWith(PublisherName.class).to("Conscious Kitten Publishers");
                    bind(Publisher.class).annotatedWith(ConsciousKitten.class).to(Key.get(Publisher.class));
                    expose(Publisher.class).annotatedWith(ConsciousKitten.class);
                }

                @Provides
                @PublisherTitles
                Set<Title> getTitles(
                        final TitleFactory factory,
                        @Named("Self Help") final Topic selfHelp,
                        @Named("Mystery") final Topic mystery
                ) {
                    final ImmutableSet.Builder<Title> builder = ImmutableSet.builder();
                    builder.add(
                            factory.create("The Purrrfect Day", "Mittens", selfHelp),
                            factory.create("What's in the Milk Bowl?", "Henri", mystery),
                            factory.create("Dogs are Dummies!", "Wiggles", selfHelp));
                    return builder.build();
                }
            });
            final Multibinder<Publisher> publishers = Multibinder.newSetBinder(binder(), Publisher.class);
            publishers.addBinding().to(Key.get(Publisher.class, ConsciousKitten.class));
        }
    }

    public static class SunnyDayPublisherModule extends AbstractModule {

        @Override
        protected void configure() {
            install(new PrivateModule() {
                @Override
                protected void configure() {
                    install(new CommonPrivatePublisherModule());
                    bindConstant().annotatedWith(PublisherName.class).to("Sunny Day Publisher");
                    bind(Publisher.class).annotatedWith(SunnyDay.class).to(Key.get(Publisher.class));
                    expose(Publisher.class).annotatedWith(SunnyDay.class);
                }

                @Provides
                @PublisherTitles
                Set<Title> getTitles(final TitleFactory factory,
                                     @Named("Science") final Topic science,
                                     @Named("Current Events") final Topic currentEvents) {
                    final ImmutableSet.Builder<Title> builder = ImmutableSet.builder();
                    builder.add(
                            factory.create("Help! The Sun is too close!", "John Mercury", science),
                            factory.create("Why am I so Happy!", "Mr. Smiles", currentEvents),
                            factory.create("Mr. Smiles doesn't look as happy as me", "Gordon Bright Eyes", currentEvents));
                    return builder.build();
                }
            });
            final Multibinder<Publisher> publishers = Multibinder.newSetBinder(binder(), Publisher.class);
            publishers.addBinding().to(Key.get(Publisher.class, SunnyDay.class));
        }
    }

    private static class TitleTypeListener implements TypeListener {

        @Override
        public <I> void hear(final TypeLiteral<I> type, final TypeEncounter<I> encounter) {
            encounter.register(new InjectionListener<I>() {
                @Override
                public void afterInjection(final I injectee) {
                    if (injectee instanceof Title) {
                        final Title title = (Title) injectee;
                        if (title.getTopic().isPresent()) {
                            title.getTopic().get().addTitle(title);
                        }
                    }
                }
            });
        }
    }
}
