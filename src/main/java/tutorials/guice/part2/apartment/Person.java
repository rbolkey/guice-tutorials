package tutorials.guice.part2.apartment;

public class Person {

    private final String name;

    public Person(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("a swell person named %s", name);
    }
}
