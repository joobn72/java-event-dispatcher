package io.herrera.event_dispatcher;

import java.lang.reflect.InvocationTargetException;

/**
 * A listener that uses an instance method.
 *
 * @author Kevin Herrera
 */
public class InstanceListener extends AbstractListener
{
    /**
     * The class instance.
     */
    private Object object;

    /**
     * The method name.
     */
    private String method;

    /**
     * Sets the class instance and method name.
     *
     * @param object The class instance.
     * @param method The method name.
     */
    public InstanceListener(Object object, String method)
    {
        this.method = method;
        this.object = object;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void invoke(Event event) throws
        IllegalAccessException,
        InvocationTargetException,
        NoSuchMethodException
    {
        reflectInvoke(object.getClass(), object, method, event);
    }
}
