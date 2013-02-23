package tutorials.guice.part3.transport;

import javax.inject.Inject;
import java.util.logging.Logger;

public class TruckMechanic implements Mechanic<Truck> {

    private Logger logger;

    @Inject
    public TruckMechanic(Logger logger) {
        this.logger = logger;
    }

    @UnavailableWhenCalled
    @Override
    public void repair(Truck vehicle) {
        logger.info(String.format("Just repaired your rugged %s.", vehicle));
    }
}
