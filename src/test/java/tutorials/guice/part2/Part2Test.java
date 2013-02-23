package tutorials.guice.part2;

import com.google.common.collect.Lists;
import com.google.inject.Module;
import org.junit.Test;
import tutorials.guice.BaseGuiceTest;
import tutorials.guice.part2.apartment.Apartment;
import tutorials.guice.part2.apartment.ApartmentConstants;
import tutorials.guice.part2.apartment.Person;

import javax.inject.Inject;
import java.util.logging.Logger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class Part2Test extends BaseGuiceTest {

    private final static Person FRED = new Person("Fred");
    private final static Person ADAM = new Person("Adam");

    @Inject
    private Logger logger;

    protected Iterable<? extends Module> getModules() {
        return Lists.newArrayList(new ApartmentModule());
    }

    @Test(expected = IllegalArgumentException.class)
    public void placeAdamAndFredInApartments() {
        final Apartment fredsApt = injector.getInstance(Apartment.class);
        final Apartment sillFredsApt = injector.getInstance(Apartment.class);

        fredsApt.occupy(FRED);
        sillFredsApt.occupy(ADAM);
    }

    @Test
    public void apartmentShouldHaveAddress() {
        final Apartment apt = injector.getInstance(Apartment.class);
        assertThat(apt.getAddress(), is(ApartmentConstants.BREEZY_LANE));
    }

    @Test
    public void shouldFetchRandomPeople() {

        for (int i = 0; i < 5; i++) {
            final Person person = injector.getInstance(Person.class);
            logger.info(person.toString());
        }
    }
}
