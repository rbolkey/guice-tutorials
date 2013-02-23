package tutorials.guice.part1.restaurant;

import javax.inject.Inject;
import java.util.List;

public class Pantry {

    private final List<String> stock;

    @Inject
    public Pantry(final List<String> stock) {

        this.stock = stock;
    }
}
