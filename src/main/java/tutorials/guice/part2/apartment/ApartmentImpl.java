package tutorials.guice.part2.apartment;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.concurrent.atomic.AtomicReference;

public class ApartmentImpl implements Apartment {

    private final AtomicReference<Person> person = new AtomicReference<>();
    private final String address;

    @Inject
    public ApartmentImpl(@Named("address") final String address) {
        this.address = address;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void occupy(final Person person) {

        final boolean occupied = this.person.compareAndSet(null, person);
        if (!occupied && !this.person.get().equals(person)) {
            throw new IllegalArgumentException(String.format("%s already occupied the apartment!", this.person.get()));
        }
    }
}
