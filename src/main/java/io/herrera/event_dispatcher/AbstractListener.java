package io.herrera.event_dispatcher;

import java.lang.reflect.InvocationTargetException;

/**
 * Manages the invocation of a listener through reflection.
 *
 * @author Kevin Herrera
 */
public abstract class AbstractListener implements Listener
{
    /**
     * Invokes a method via the class reflection.
     *
     * There are three attempts made at invoking the method for a class:
     *
     * # Using the class reflection for the event as an argument.
     * # Using the reflection for the Listener interface as an argument.
     * # Using no arguments.
     *
     * If it is necessary, the method will traverse up the class hierarchy
     * through the class reflection to find the method that needs to be invoked.
     * For this reason, it is advised that you pass the reflection of the class
     * for the object that is being given.
     *
     * @param reflectClass The class reflection.
     * @param object       The class instance (if any).
     * @param method       The method name.
     * @param event        The dispatched event.
     *
     * @throws IllegalAccessException    If the method is not public.
     * @throws InvocationTargetException If the method throws an exception.
     * @throws NoSuchMethodException     If the method does not exist.
     */
    protected void reflectInvoke(
        Class<?> reflectClass,
        Object object,
        String method,
        Event event
    ) throws
        IllegalAccessException,
        InvocationTargetException,
        NoSuchMethodException
    {
        while (null != reflectClass) {
            try {
                reflectClass
                    .getDeclaredMethod(method, event.getClass())
                    .invoke(object, event);

                break;
            } catch (NoSuchMethodException exception1) {
                try {
                    reflectClass
                        .getDeclaredMethod(method, Event.class)
                        .invoke(object, event);

                    break;
                } catch (NoSuchMethodException exception2) {
                    try {
                        reflectClass
                            .getDeclaredMethod(method)
                            .invoke(object);

                        break;
                    } catch (NoSuchMethodException exception3) {
                        reflectClass = reflectClass.getSuperclass();
                    }
                }
            }
        }
    }
}
