package tutorials.guice.part3.transport.impl;

import tutorials.guice.part3.transport.Vehicle;
import tutorials.guice.part3.transport.Wheel;

import javax.inject.Inject;

public class BicycleWheel implements Wheel {

    private final Vehicle owner;

    @Inject
    public BicycleWheel(@Bicycles final Vehicle owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("BicyleWheel");
        sb.append("{owner=").append(owner.getClass());
        sb.append('}');
        return sb.toString();
    }
}
