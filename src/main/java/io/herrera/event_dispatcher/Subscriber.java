package io.herrera.event_dispatcher;

import java.util.Map;
import java.util.List;

/**
 * A subscriber contains listeners for one or more event.
 *
 * @author Kevin Herrera
 */
public interface Subscriber
{
    /**
     * Returns the listeners in the subscriber.
     *
     * @return The listeners in the subscriber.
     */
    public Map<String,Map<Integer,List<Listener>>> getListeners();
}
