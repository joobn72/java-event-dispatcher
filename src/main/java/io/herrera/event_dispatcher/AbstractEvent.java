package io.herrera.event_dispatcher;

/**
 * A basic implementation of an event.
 *
 * @author Kevin Herrera
 */
public abstract class AbstractEvent implements Event
{
    /**
     * The flag used to determine if event propagation has been stopped.
     */
    private boolean propagationStopped = false;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isPropagationStopped()
    {
        return propagationStopped;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopPropagation()
    {
        propagationStopped = true;
    }
}
