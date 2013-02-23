package tutorials.guice.part1.restaurant;

import javax.inject.Inject;
import java.util.logging.Logger;

public class Kitchen {

    private final Logger logger;

    @Inject
    public Kitchen(final Logger logger) {
        this.logger = logger;
    }

    void order(final Dish dish) {
        logger.info(String.format("Cannot prepare %s! The Kitchen is closed!", dish));
    }
}
