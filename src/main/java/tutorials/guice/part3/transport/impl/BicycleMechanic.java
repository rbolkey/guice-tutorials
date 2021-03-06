package tutorials.guice.part3.transport.impl;

import tutorials.guice.part3.transport.Mechanic;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BicycleMechanic implements Mechanic<Bicycle> {

    private final Logger logger;

    @Inject
    public BicycleMechanic(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void repair(final Bicycle vehicle) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(new StringBuilder("Just repaired your sweet ").append(vehicle).toString());
        }
    }
}
