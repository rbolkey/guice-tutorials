package tutorials.guice.part2;

import com.google.common.collect.Lists;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Names;
import tutorials.guice.part2.apartment.Apartment;
import tutorials.guice.part2.apartment.ApartmentConstants;
import tutorials.guice.part2.apartment.ApartmentImpl;
import tutorials.guice.part2.apartment.Person;

import java.util.List;
import java.util.Random;

public class ApartmentModule extends AbstractModule {

    private static final List<String> PEOPLE = Lists.newArrayList("Adam", "Bruce", "Carol", "Diana");

    @Override
    protected void configure() {
        // bind the interface to the concrete class and ensure there is just one instance of the class
        // Note: Apartment references are Singletons .. ApartmentImpl references are NOT!
        bind(Apartment.class).to(ApartmentImpl.class).in(Singleton.class);
        // bind the string constant annotated with Name("address") to this constant
        bindConstant().annotatedWith(Names.named("address")).to(ApartmentConstants.BREEZY_LANE);
        // ensure the class Random is only instantiated once
        bind(Random.class).in(Singleton.class);
    }

    /**
     * Provides a random person from a set of names, could also be generated via a {@link com.google.inject.Provider} instance.
     */
    @Provides
    Person person(final Random random) {
        final int index = random.nextInt(PEOPLE.size());
        return new Person(PEOPLE.get(index));
    }
}
