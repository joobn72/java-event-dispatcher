package io.herrera.event_dispatcher;

import java.util.HashMap;
import java.util.Map;

/**
 * A dispatcher implementation for listeners.
 *
 * @author Kevin Herrera
 */
public class ListenerDispatcher implements Dispatcher
{
    /**
     * The collections of listeners
     */
    private Map<String,ListenerCollection> listeners;

    /**
     * Creates a new collection map of listeners to use.
     */
    public ListenerDispatcher()
    {
        this(new HashMap<String,ListenerCollection>());
    }

    /**
     * Sets the collection map of listeners to use.
     */
    public ListenerDispatcher(Map<String,ListenerCollection> listeners)
    {
        this.listeners = listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Event dispatch(String name, Event event) throws Exception
    {
        if (listeners.containsKey(name)) {
            for (Listener listener : listeners.get(name).getAllFlat()) {
                listener.invoke(event);
            }
        }

        return event;
    }

    /**
     * {@inheritDoc}
     */
    public Map<String,ListenerCollection> getListeners()
    {
        return listeners;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerDispatcher registerListener(String name, Listener listener)
    {
        if (!listeners.containsKey(name)) {
            listeners.put(name, new ListenerCollection());
        }

        listeners.get(name).add(listener);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerDispatcher registerListener(
        String name,
        Listener listener,
        Integer priority
    ) {
        if (!listeners.containsKey(name)) {
            listeners.put(name, new ListenerCollection());
        }

        listeners.get(name).add(listener, priority);

        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ListenerDispatcher unregisterListener(String name, Listener listener)
    {
        if (listeners.containsKey(name)) {
            listeners.get(name).remove(listener);
        }

        return this;
    }
}
