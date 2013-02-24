package tutorials.guice.part3.transport.impl;

import tutorials.guice.part3.transport.Vehicle;
import tutorials.guice.part3.transport.Wheel;

import javax.inject.Inject;

public class Bicycle implements Vehicle {

    private final Wheel frontWheel;
    private final Wheel rearWheel;

    @Inject
    public Bicycle(@Bicycles final Wheel frontWheel, @Bicycles final Wheel rearWheel) {
        this.frontWheel = frontWheel;
        this.rearWheel = rearWheel;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Bicycle");
        sb.append("{frontWheel=").append(frontWheel);
        sb.append(", rearWheel=").append(rearWheel);
        sb.append('}');
        return sb.toString();
    }
}
