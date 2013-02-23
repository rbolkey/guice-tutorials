package tutorials.guice.part1.restaurant;

public class Dish {

    private final String name;

    public Dish(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return new StringBuilder("a very tasty dish of ").append(name).toString();
    }
}
