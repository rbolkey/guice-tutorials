package tutorials.guice.part1;

import com.google.inject.ConfigurationException;
import org.junit.Test;
import tutorials.guice.BaseGuiceTest;
import tutorials.guice.part1.restaurant.Pantry;
import tutorials.guice.part1.restaurant.Restaurant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

public class Part1Test extends BaseGuiceTest {

    @Test
    public void shouldRunTheRestaurant() {
        final Restaurant restaurant = injector.getInstance(Restaurant.class);
        restaurant.run();
    }

    @Test
    public void instanceVersionsShouldBeDifferent() {

        final Restaurant restaurant1 = injector.getInstance(Restaurant.class);
        final Restaurant restaurant2 = injector.getInstance(Restaurant.class);
        assertThat(restaurant1, not(equalTo(restaurant2)));
        assertThat(restaurant1.getKitchen(), not(equalTo(restaurant2.getKitchen())));
    }

    @Test(expected = ConfigurationException.class)
    public void restaurantShouldNotBeAccessibleViaInterface() {
        injector.getInstance(Runnable.class);
    }

    @Test(expected = ConfigurationException.class)
    public void pantryShouldHaveMissingDependencies() {
        injector.getInstance(Pantry.class);
    }
}
