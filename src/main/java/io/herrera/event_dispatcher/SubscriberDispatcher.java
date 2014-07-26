package io.herrera.event_dispatcher;

import java.util.List;
import java.util.Map;

/**
 * A dispatcher for subscribers and listeners.
 *
 * @author Kevin Herrera
 */
public class SubscriberDispatcher extends ListenerDispatcher
{
    /**
     * Registers an event subscriber.
     *
     * @param subscriber The subscriber to register.
     */
    public SubscriberDispatcher registerSubscriber(Subscriber subscriber)
    {
        Map<String,Map<Integer,List<Listener>>> names = subscriber.getListeners();

        for (String name : names.keySet()) {
            Map<Integer,List<Listener>> priorities = names.get(name);

            for (Integer priority : priorities.keySet()) {
                for (Listener listener : priorities.get(priority)) {
                    registerListener(name, listener, priority);
                }
            }
        }

        return this;
    }

    /**
     * Unregisters an event subscriber.
     *
     * @param subscriber The subscriber to unregister.
     */
    public SubscriberDispatcher unregisterSubscriber(Subscriber subscriber)
    {
        Map<String,Map<Integer,List<Listener>>> names = subscriber.getListeners();

        for (String name : names.keySet()) {
            Map<Integer,List<Listener>> priorities = names.get(name);

            for (Integer priority : priorities.keySet()) {
                for (Listener listener : priorities.get(priority)) {
                    unregisterListener(name, listener);
                }
            }
        }

        return this;
    }
}
