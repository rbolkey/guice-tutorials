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
        return new StringBuilder("a very tasty dish of ").append(getName()).toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Dish dish = (Dish) o;

        if (!name.equals(dish.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
