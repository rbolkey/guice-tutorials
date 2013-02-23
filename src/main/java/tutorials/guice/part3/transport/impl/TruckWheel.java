package tutorials.guice.part3.transport.impl;

import tutorials.guice.part3.transport.Vehicle;
import tutorials.guice.part3.transport.Wheel;

import javax.inject.Inject;

public class TruckWheel implements Wheel {

    private final Vehicle owner;

    @Inject
    public TruckWheel(@Trucks final Vehicle owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("TruckWheel");
        sb.append("{owner=").append(owner.getClass());
        sb.append('}');
        return sb.toString();
    }
}
