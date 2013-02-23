package tutorials.guice.part3;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import tutorials.guice.part3.transport.*;
import tutorials.guice.part3.transport.impl.*;

import java.util.Random;
import java.util.logging.Logger;

public class TransportModule extends AbstractModule {

    @Override
    protected void configure() {

        // Setup a mechanic for repairing truck along with the truck
        bind(new TypeLiteral<Mechanic<Truck>>() {}).to(TruckMechanic.class);
        bind(Wheel.class).annotatedWith(Trucks.class).to(TruckWheel.class);
        bind(Vehicle.class).annotatedWith(Trucks.class).to(Truck.class);

        // Setup a mechanic for repairing bicycles along with the bicycle
        bind(new TypeLiteral<Mechanic<Bicycle>>() {}).to(BicycleMechanic.class);
        bind(Wheel.class).annotatedWith(Bicycles.class).to(BicycleWheel.class);
        bind(Vehicle.class).annotatedWith(Bicycles.class).to(Bicycle.class);

        // AOP example
        // first matcher parameter matches classes and the second methods
        bindInterceptor(
                Matchers.subclassesOf(Mechanic.class),
                Matchers.annotatedWith(UnavailableWhenCalled.class),
                new OnPhoneCallInterceptor()
        );
    }

    private static class OnPhoneCallInterceptor implements MethodInterceptor {

        private final Logger logger = Logger.getLogger(OnPhoneCallInterceptor.class.getName());

        private final Random random = new Random();

        @Override
        public Object invoke(MethodInvocation invocation) throws Throwable {

            // 1/3 chance of being on call!
            boolean onCall = random.nextInt(3) == 0;

            if (onCall) {
                // Yes, typically you SHOULDN'T log and throw.
                final String message = "Sorry! I'm on the phone!";
                logger.warning(message);
                throw new IllegalStateException(message);
            }
            return invocation.proceed();
        }
    }
}
