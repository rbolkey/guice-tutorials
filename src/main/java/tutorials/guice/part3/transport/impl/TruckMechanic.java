package tutorials.guice.part3.transport.impl;

import tutorials.guice.part3.transport.Mechanic;
import tutorials.guice.part3.transport.UnavailableWhenCalled;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TruckMechanic implements Mechanic<Truck> {

    private Logger logger;

    @Inject
    public TruckMechanic(final Logger logger) {
        this.logger = logger;
    }

    @UnavailableWhenCalled
    @Override
    public void repair(final Truck vehicle) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(new StringBuilder("Just repaired your rugged ").append(vehicle).toString());
        }
    }
}
