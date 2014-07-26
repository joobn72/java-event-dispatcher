package io.herrera.event_dispatcher;

import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Verifies that the listener collection manager functions as intended.
 *
 * @author Kevin Herrera
 */
public class ListenerCollectionTest extends TestCase
{
    /**
     * The instance being tested.
     */
    private ListenerCollection collection;

    /**
     * Verifies that we can add to the collection.
     */
    public void testAdd()
    {
        DeadListener a = new DeadListener();
        DeadListener b = new DeadListener();
        DeadListener c = new DeadListener();
        DeadListener d = new DeadListener();
        DeadListener e = new DeadListener();
        DeadListener f = new DeadListener();
        DeadListener g = new DeadListener();

        collection.add(a, 4);
        collection.add(b);
        collection.add(c, 2);
        collection.add(d);
        collection.add(e, 3);
        collection.add(f);
        collection.add(g, 1);

        ArrayList<Listener> expected = new ArrayList<>();

        expected.add(b);
        expected.add(d);
        expected.add(f);
        expected.add(g);
        expected.add(c);
        expected.add(e);
        expected.add(a);

        assertEquals(
            "The listeners should have been returned in a flat, ordered array.",
            expected,
            collection.getAllFlat()
        );
    }

    /**
     * Verifies that we can remove all occurrences of a listener.
     */
    public void testRemove()
    {
        DeadListener a = new DeadListener();
        DeadListener b = new DeadListener();
        DeadListener c = new DeadListener();

        collection.add(a);
        collection.add(b, 1);
        collection.add(c);
        collection.add(a, 2);
        collection.remove(a);

        ArrayList<Listener> expected = new ArrayList<>();

        expected.add(c);
        expected.add(b);

        assertEquals(
            "All occurrences of the listener should have been removed.",
            expected,
            collection.getAllFlat()
        );
    }

    /**
     * Verifies that we can remove all listeners.
     */
    public void testRemoveAll()
    {
        DeadListener a = new DeadListener();
        DeadListener b = new DeadListener();
        DeadListener c = new DeadListener();

        collection.add(a);
        collection.add(b, 1);
        collection.add(c);
        collection.add(a, 2);
        collection.removeAll();

        assertEquals(
            "All listeners should have been removed.",
            new ArrayList<Listener>(),
            collection.getAllFlat()
        );
    }

    /**
     * Creates a new instance for testing.
     */
    protected void setUp()
    {
        collection = new ListenerCollection();
    }

    /**
     * A listener that does nothing.
     */
    private class DeadListener implements Listener
    {
        /**
         * {@inheritDoc}
         */
        public void invoke(Event event)
        {
        }
    }
}
