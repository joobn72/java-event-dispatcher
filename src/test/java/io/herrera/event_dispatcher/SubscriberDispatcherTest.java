package io.herrera.event_dispatcher;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Verifies that the subscriber dispatcher functions as intended.
 *
 * @author Kevin Herrera
 */
public class SubscriberDispatcherTest extends TestCase
{
    /**
     * The instance being tested.
     */
    private SubscriberDispatcher dispatcher;

    /**
     * The instance being used for testing.
     */
    private TestSubscriber subscriber;

    /**
     * Verifies that we can register a subscriber.
     */
    public void testRegisterSubscriber()
    {
        assertSame(
            "The dispatcher should have returned itself.",
            dispatcher,
            dispatcher.registerSubscriber(subscriber)
        );

        Map<String,ListenerCollection> collection = dispatcher.getListeners();

        assertTrue(
            "The event should have been created.",
            collection.containsKey("test")
        );

        List<Listener> expected = new ArrayList<>();

        expected.add(subscriber.getListeners().get("test").get(0).get(0));

        assertEquals(
            "The listener from the subscriber should have been registered.",
            expected,
            collection.get("test").getAllFlat()
        );
    }

    /**
     * Verifies that we can unregister a subscriber.
     */
    public void testUnregisterSubscriber()
    {
        dispatcher.registerSubscriber(subscriber);

        assertSame(
            "The dispatcher should have returned itself.",
            dispatcher,
            dispatcher.unregisterSubscriber(subscriber)
        );

        assertEquals(
            "The listener from the subscriber should have been unregistered.",
            new ArrayList<Listener>(),
            dispatcher.getListeners().get("test").getAllFlat()
        );
    }

    /**
     * Creates a new instance for testing.
     */
    protected void setUp()
    {
        dispatcher = new SubscriberDispatcher();
        subscriber = new TestSubscriber();
    }

    /**
     * A simple test listener.
     */
    private class TestListener implements Listener
    {
        /**
         * {@inheritDoc}
         */
        public void invoke(Event event)
        {
        }
    }

    /**
     * A simple test subscriber.
     */
    private class TestSubscriber implements Subscriber
    {
        /**
         * The list of event listeners.
         */
        private Map<String,Map<Integer,List<Listener>>> events;

        /**
         * Creates the list of listeners.
         */
        public TestSubscriber()
        {
            List<Listener> listeners = new ArrayList<>();
            listeners.add(new TestListener());

            Map<Integer, List<Listener>> priorities = new HashMap<>();
            priorities.put(0, listeners);

            events = new HashMap<>();
            events.put("test", priorities);
        }

        /**
         * {@inheritDoc}
         */
        public Map<String,Map<Integer,List<Listener>>> getListeners()
        {
            return events;
        }
    }
}
