package io.herrera.event_dispatcher;

import junit.framework.TestCase;

/**
 * Verifies that the instance listener functions as intended.
 *
 * @author Kevin Herrera
 */
public class StaticListenerTest extends TestCase
{
    /**
     * A simple test class that will act as our listener.
     *
     * @author Kevin Herrera
     */
    private static class TestClass
    {
        /**
         * Indicates whether or not this listener was invoked.
         */
        public static boolean saidHi = false;

        /**
         * The listening method to invoke.
         *
         * @param event The event.
         */
        public static void hello(Event event)
        {
            saidHi = true;
        }
    }

    /**
     * Verifies that the instance listener is invoked.
     */
    public void testInvoke() throws Exception
    {
        AbstractEvent event = new AbstractEvent(){};

        new StaticListener(TestClass.class, "hello").invoke(event);

        assertTrue(
            "The static listener should have been invoked.",
            TestClass.saidHi
        );
    }
}
