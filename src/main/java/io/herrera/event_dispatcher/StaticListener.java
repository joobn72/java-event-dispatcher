package io.herrera.event_dispatcher;

import java.lang.reflect.InvocationTargetException;

/**
 * A listener that uses a static method.
 *
 * @author Kevin Herrera
 */
public class StaticListener extends AbstractListener
{
    /**
     * The class reflection.
     */
    private Class<?> reflectClass;

    /**
     * The method name.
     */
    private String method;

    /**
     * Sets the class reflection and method name.
     *
     * @param reflectClass The class reflection.
     * @param method       The method name.
     */
    public StaticListener(Class<?> reflectClass, String method)
    {
        this.method = method;
        this.reflectClass = reflectClass;
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
        reflectInvoke(reflectClass, null, method, event);
    }
}
