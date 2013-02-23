package tutorials.guice.part1.restaurant;

import javax.inject.Inject;

public class Restaurant implements Runnable {

    private static final Dish lasagna = new Dish("lasagna");

    private final Kitchen kitchen;

    @Inject
    public Restaurant(final Kitchen kitchen) {

        this.kitchen = kitchen;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void run() {
        kitchen.order(lasagna);
    }
}
