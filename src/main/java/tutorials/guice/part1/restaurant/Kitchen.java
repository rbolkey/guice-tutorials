package tutorials.guice.part1.restaurant;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kitchen {

    private final Logger logger;

    @Inject
    public Kitchen(final Logger logger) {
        this.logger = logger;
    }

    void order(final Dish dish) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(new StringBuilder("Cannot prepare ").append(dish).append("! The Kitchen is closed!").toString());
        }
    }
}
