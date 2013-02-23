package tutorials.guice.part3;

import com.google.common.collect.Lists;
import com.google.inject.Key;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import org.junit.Test;
import tutorials.guice.BaseGuiceTest;
import tutorials.guice.part3.transport.impl.Bicycle;
import tutorials.guice.part3.transport.Mechanic;
import tutorials.guice.part3.transport.impl.Truck;
import tutorials.guice.part3.transport.Vehicle;
import tutorials.guice.part3.transport.impl.Trucks;

import javax.inject.Inject;
import javax.inject.Provider;
import java.util.logging.Logger;

public class Part3Test extends BaseGuiceTest {

    // injection of a generic type
    @Inject
    private Mechanic<Bicycle> bicycleMechanic;

    // injection of the type literal for reflection information
    @Inject
    private TypeLiteral<Mechanic<Bicycle>> bicycleMechanicLiteral;

    // injection of the provider
    @Inject
    @Trucks
    private Provider<Vehicle> truck;

    @Inject
    private Mechanic<Truck> truckMechanic;

    @Inject
    private Logger logger;

    @Override
    protected Iterable<? extends Module> getModules() {
        return Lists.newArrayList(new TransportModule());
    }

    // using an injected parameterized type
    @Test
    public void shouldRepairTheBicycle() {
        final Bicycle myBike = injector.getInstance(Bicycle.class);
        bicycleMechanic.repair(myBike);
        logger.info(bicycleMechanicLiteral.toString());
    }

    // fetching the instance of parameterized type using a type literal
    @Test
    public void shouldAlsoRepairTheBicycle() {
        final Bicycle myBike = injector.getInstance(Bicycle.class);
        final Mechanic<Bicycle> otherMechanic = injector.getInstance(Key.get(bicycleMechanicLiteral));
        otherMechanic.repair(myBike);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowAnExceptionWhenOnCallDueToAspect() {
        final Truck myTruck = injector.getInstance(Truck.class);
        for (int i = 0; i < 12; i++) {
            truckMechanic.repair(myTruck);
        }
    }

    // using a circular proxy (where each implementation depends upon the other's interface.
    @Test
    public void shouldCreateTruckUsingCircularProxies() {
        final Truck truck = injector.getInstance(Truck.class);
        logger.info(truck.toString());
    }
}
