package io.herrera.event_dispatcher;

import junit.framework.TestCase;

/**
 * Verifies that the event class works as intended.
 *
 * @author Kevin Herrera
 */
public class AbstractEventTest extends TestCase
{
    /**
     * The instance being tested.
     */
    private AbstractEvent abstractEvent;

    /**
     * Verifies that we can check if event propagation has been stopped.
     */
    public void testIsPropagationStopped() throws Exception
    {
        assertFalse(
            "The event propagation SHOULD be allowed to continue.",
            abstractEvent.isPropagationStopped()
        );

        abstractEvent.stopPropagation();

        assertTrue(
            "The event propagation SHOULD NOT be allowed to continue.",
            abstractEvent.isPropagationStopped()
        );
    }

    /**
     * Sets up the instance being tested.
     */
    protected void setUp()
    {
        abstractEvent = new AbstractEvent(){};
    }
}
