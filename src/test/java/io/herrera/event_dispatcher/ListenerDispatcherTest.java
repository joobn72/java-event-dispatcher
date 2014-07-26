package io.herrera.event_dispatcher;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Verifies that the listener dispatcher functions as intended.
 *
 * @author Kevin Herrera
 */
public class ListenerDispatcherTest extends TestCase
{
    /**
     * The instance being tested.
     */
    private ListenerDispatcher dispatcher;

    /**
     * The collection of listeners being used for testing.
     */
    private HashMap<String,ListenerCollection> listeners;

    /**
     * Verifies that a collection is created and can be set.
     */
    public void testConstruct()
    {
        assertSame(
            "The dispatcher should use the provided hash map.",
            listeners,
            dispatcher.getListeners()
        );

        assertTrue(
            "A hash map should have been set for the listeners",
            (new ListenerDispatcher()).getListeners() instanceof HashMap
        );
    }

    /**
     * Verifies that we can dispatch an event.
     */
    public void testDispatch() throws Exception
    {
        AbstractEvent event = new AbstractEvent() {};
        TestListener listener = new TestListener();

        listeners.get("test").add(listener);

        assertSame(
            "The same event should have been returned.",
            event,
            dispatcher.dispatch("test", event)
        );

        assertTrue(
            "The listener should have been invoked.",
            listener.invoked
        );
    }

    /**
     * Verifies that we can register a listener.
     */
    public void testRegisterListener()
    {
        TestListener a = new TestListener();
        TestListener b = new TestListener();
        TestListener c = new TestListener();

        dispatcher.registerListener("test2", a);
        dispatcher.registerListener("test3", b, 2);
        dispatcher.registerListener("test3", c, 1);

        assertTrue(
            "The test2 event should have been created.",
            listeners.containsKey("test2")
        );

        ArrayList<Listener> expected = new ArrayList<>();

        expected.add(a);
        expected.add(c);
        expected.add(b);

        ArrayList<Listener> actual = new ArrayList<>();

        actual.addAll(listeners.get("test2").getAllFlat());
        actual.addAll(listeners.get("test3").getAllFlat());

        assertEquals(
            "The listeners should have been registered with priority order.",
            expected,
            actual
        );
    }

    /**
     * Verifies that we can unregister a listener.
     */
    public void testUnregisterListener()
    {
        TestListener listener = new TestListener();

        listeners.get("test").add(listener);
        dispatcher.unregisterListener("test", listener);

        assertEquals(
            "The listener should have been unregistered.",
            new ArrayList<Listener>(),
            listeners.get("test").getAllFlat()
        );
    }

    /**
     * Creates a new instance for testing.
     */
    protected void setUp()
    {
        listeners = new HashMap<>();
        listeners.put("test", new ListenerCollection());

        dispatcher = new ListenerDispatcher(listeners);
    }

    /**
     * A simple test listener.
     */
    private class TestListener implements Listener
    {
        /**
         * The flag that indicates that this listener was invoked.
         */
        public boolean invoked = false;

        /**
         * {@inheritDoc}
         */
        public void invoke(Event event)
        {
            invoked = true;
        }
    }
}
