package tutorials.guice.part3.transport;

import javax.inject.Inject;
import java.util.logging.Logger;

public class BicycleMechanic implements Mechanic<Bicycle> {

    private final Logger logger;

    @Inject
    public BicycleMechanic(final Logger logger) {
        this.logger = logger;
    }

    @Override
    public void repair(final Bicycle vehicle) {
        logger.info(String.format("Just repaired your sweet %s.", vehicle));
    }
}
