package io.herrera.event_dispatcher;

/**
 * An event is used to manage propagation to listeners and contextual data.
 *
 * A simple event will simply manage the propagation of itself to other
 * listeners when the event is dispatched. More advanced events will also
 * provide other members that make contextual data available. This data
 * could be used or modified by the listeners to affect the outcome of a
 * dispatched event.
 *
 * @author Kevin Herrera
 */
public interface Event
{
    /**
     * Checks if event propagation has been stopped.
     *
     * @return If event propagation has been stopped, `true` is returned.
     *         Otherwise, event propagation may continue and `false` is
     *         returned.
     */
    public boolean isPropagationStopped();

    /**
     * Stops further event propagation to the remaining listeners.
     */
    public void stopPropagation();
}
