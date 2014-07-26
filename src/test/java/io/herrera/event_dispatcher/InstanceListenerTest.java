package io.herrera.event_dispatcher;

import junit.framework.TestCase;

/**
 * Verifies that the instance listener functions as intended.
 *
 * @author Kevin Herrera
 */
public class InstanceListenerTest extends TestCase
{
    /**
     * A simple test class that will act as our listener.
     *
     * @author Kevin Herrera
     */
    private class TestClass
    {
        /**
         * Indicates whether or not this listener was invoked.
         */
        public boolean saidHi = false;

        /**
         * The listening method to invoke.
         *
         * @param event The event.
         */
        public void hello(Event event)
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
        TestClass object = new TestClass();

        new InstanceListener(object, "hello").invoke(event);

        assertTrue(
            "The instance listener should have been invoked.",
            object.saidHi
        );
    }
}
