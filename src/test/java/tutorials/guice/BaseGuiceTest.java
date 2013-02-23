package tutorials.guice;

import com.google.common.collect.Lists;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import org.junit.Before;
import org.slf4j.bridge.SLF4JBridgeHandler;

public abstract class BaseGuiceTest {

    protected Injector injector;

    static {
        // Optionally remove existing handlers attached to j.u.l root logger
        SLF4JBridgeHandler.removeHandlersForRootLogger();  // (since SLF4J 1.6.5)

        // add SLF4JBridgeHandler to j.u.l's root logger, should be done once during
        // the initialization phase of your application
        SLF4JBridgeHandler.install();
    }

    @Before
    public void setUp() {
        this.injector = Guice.createInjector(getModules());
        injector.injectMembers(this);
    }

    protected Iterable<? extends Module> getModules() {
        return Lists.newArrayList();
    }
}
