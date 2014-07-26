package io.herrera.event_dispatcher;

/**
 * A listener that can observer dispatched events.
 *
 * @author Kevin Herrera
 */
public interface Listener
{
    /**
     * Invokes the listener using the dispatched event.
     *
     * @param event The dispatched event.
     *
     * @throws Exception If the listener could not be invoked.
     */
    public void invoke(Event event) throws Exception;
}
