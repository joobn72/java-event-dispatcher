package io.herrera.event_dispatcher;

import java.util.Map;

/**
 * A dispatcher manages event listeners and dispatches them.
 *
 * An event dispatcher will keep a registry of event listeners and what events
 * they are registered to. When the listeners are needed, a dispatch can be
 * issued for a specific event using that event's `Event` object. That object
 * will then be passed on to the registered listeners.
 *
 * @author Kevin Herrera
 */
public interface Dispatcher
{
    /**
     * Dispatches an event.
     *
     * @param name  The name of the event.
     * @param event The event object.
     *
     * @return The same event.
     *
     * @throws Exception If a listener could not be updated.
     */
    public Event dispatch(String name, Event event) throws Exception;

    /**
     * Returns the collection map of listeners for the dispatcher.
     *
     * @return The collection map of listeners.
     */
    public Map<String,ListenerCollection> getListeners();

    /**
     * Registers an event listener for a specific event.
     *
     * @param name     The name of the event.
     * @param listener The listener to register.
     *
     * @return The event dispatcher.
     */
    public Dispatcher registerListener(String name, Listener listener);

    /**
     * Registers an event listener for a specific event and priority.
     *
     * @param name     The name of the event.
     * @param listener The listener to register.
     * @param priority The priority of the listener.
     *
     * @return The event dispatcher.
     */
    public Dispatcher registerListener(
        String name,
        Listener listener,
        Integer priority
    );

    /**
     * Unregisters all occurrences of an event listener for a specific event.
     *
     * @param name     The name of the event.
     * @param listener The listener to unregister.
     *
     * @return The event dispatcher.
     */
    public Dispatcher unregisterListener(String name, Listener listener);
}
