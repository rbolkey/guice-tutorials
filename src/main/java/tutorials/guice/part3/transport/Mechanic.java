package tutorials.guice.part3.transport;

public interface Mechanic<T extends Vehicle> {

    void repair(T vehicle);
}
