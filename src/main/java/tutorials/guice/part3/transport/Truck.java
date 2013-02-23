package tutorials.guice.part3.transport;

import tutorials.guice.part3.Trucks;

import javax.inject.Inject;

public class Truck implements Vehicle {

    private final Wheel frontRight;
    private final Wheel frontLeft;
    private final Wheel rearRight;
    private final Wheel rearLeft;

    @Inject
    public Truck(@Trucks final Wheel frontRight, @Trucks final Wheel frontLeft, @Trucks final Wheel rearRight, @Trucks final Wheel rearLeft) {
        this.frontRight = frontRight;
        this.frontLeft = frontLeft;
        this.rearRight = rearRight;
        this.rearLeft = rearLeft;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("Truck");
        sb.append("{frontLeft=").append(frontLeft);
        sb.append(", frontRight=").append(frontRight);
        sb.append(", rearRight=").append(rearRight);
        sb.append(", rearLeft=").append(rearLeft);
        sb.append('}');
        return sb.toString();
    }
}
