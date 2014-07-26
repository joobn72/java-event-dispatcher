package io.herrera.event_dispatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Manages a collection of event listeners grouped by priority.
 *
 * @author Kevin Herrera
 */
public class ListenerCollection
{
    /**
     * The collection of listeners in priority order.
     */
    private HashMap<Integer, ArrayList<Listener>> listeners = new HashMap<>();

    /**
     * Adds an event listener to the collection with the priority of 0 (zero).
     *
     * @param listener The listener to add.
     */
    public ListenerCollection add(Listener listener)
    {
        return add(listener, 0);
    }

    /**
     * Adds an event listener to the collection.
     *
     * If a priority is not given, it will default to 0 (zero).
     *
     * @param listener The listener to add.
     * @param priority The priority of the listener.
     *
     * @return The listener collection.
     */
    public ListenerCollection add(Listener listener, Integer priority)
    {
        if (!listeners.containsKey(priority)) {
            listeners.put(priority, new ArrayList<Listener>());
        }

        listeners.get(priority).add(listener);

        return this;
    }

    /**
     * Returns the event listeners sorted into a flat structure.
     */
    public ArrayList<Listener> getAllFlat()
    {
        ArrayList<Listener> flat = new ArrayList<>();
        ArrayList<Integer> priorities = new ArrayList<>(listeners.keySet());

        for (Integer priority : priorities) {
            flat.addAll(listeners.get(priority));
        }

        return flat;
    }

    /**
     * Removes all occurrences of an event listener from the collection.
     *
     * @param listener The listener to remove.
     *
     * @return The listener collection.
     */
    public ListenerCollection remove(Listener listener)
    {
        for (Integer priority : listeners.keySet()) {
            listeners.get(priority).removeAll(Collections.singleton(listener));
        }

        return this;
    }

    /**
     * Removes all event listeners from the collection.
     *
     * @return The listener collection.
     */
    public ListenerCollection removeAll()
    {
        listeners.clear();

        return this;
    }
}
