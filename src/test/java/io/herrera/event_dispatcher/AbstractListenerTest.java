package io.herrera.event_dispatcher;

import junit.framework.TestCase;

/**
 * Verifies that the abstract listener functions as intended.
 *
 * @author Kevin Herrera
 */
public class AbstractListenerTest extends TestCase
{
    /**
     * Verifies that the listener can be invoked.
     */
    public void testReflectMethod() throws Exception
    {
        GenericEvent event = new GenericEvent();

        ClassSpecific classObject = new ClassSpecific();
        GenericListener classListener = new GenericListener(
            ClassSpecific.class,
            classObject,
            "update"
        );

        classListener.invoke(event);

        assertTrue(
            "The class specific event listener should have been updated.",
            classObject.updated
        );

        InterfaceSpecific interfaceObject = new InterfaceSpecific();
        GenericListener interfaceListener = new GenericListener(
            InterfaceSpecific.class,
            interfaceObject,
            "update"
        );


        interfaceListener.invoke(event);

        assertTrue(
            "The interface specific event listener should have been updated.",
            interfaceObject.updated
        );

        NoneAccepted noneObject = new NoneAccepted();
        GenericListener noneListener = new GenericListener(
            NoneAccepted.class,
            noneObject,
            "update"
        );

        noneListener.invoke(event);

        assertTrue(
            "The no-argument event listener should have been updated.",
            noneObject.updated
        );

        SuperClassSpecific superObject = new SuperClassSpecific();
        GenericListener superListener = new GenericListener(
            SuperClassSpecific.class,
            superObject,
            "update"
        );

        superListener.invoke(event);

        assertTrue(
            "The super class listener method should have been invoked.",
            superObject.updated
        );
    }

    /**
     * Triggers a path of logic where a class specific argument is expected.
     */
    private class ClassSpecific
    {
        /**
         * The flag that determines if a listener was updated.
         */
        public boolean updated = false;

        /**
         * The method that is updated when an event is dispatched.
         *
         * @param event The event.
         */
        public void update(GenericEvent event)
        {
            updated = true;
        }
    }

    /**
     * A simple test event.
     */
    private class GenericEvent extends AbstractEvent
    {
    }

    /**
     * Triggers a path of logic where a class specific argument is expected.
     */
    private class GenericListener extends AbstractListener
    {
        /**
         * The event listener object.
         */
        public Object object;

        /**
         * The reflection class object
         */
        public Class<?> objectClass;

        /**
         * The method to invoke.
         */
        public String method;

        /**
         * Sets the event listener object, class reflection, and method name.
         *
         * @param objectClass The class reflection.
         * @param object      The event listener object.
         * @param method      The name of the method.
         */
        public GenericListener(
            Class<?> objectClass,
            Object object,
            String method
        ) {
            this.method = method;
            this.object = object;
            this.objectClass = objectClass;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public void invoke(Event event) throws Exception
        {
            reflectInvoke(objectClass, object, method, event);
        }
    }

    /**
     * Triggers a path of logic where an interface specific argument is expected.
     */
    private class InterfaceSpecific
    {
        /**
         * The flag that determines if a listener was updated.
         */
        public boolean updated = false;

        /**
         * The method that is updated when an event is dispatched.
         *
         * @param event The event.
         */
        public void update(Event event)
        {
            updated = true;
        }
    }

    /**
     * Triggers a path of logic where no argument is expected.
     */
    private class NoneAccepted
    {
        /**
         * The flag that determines if a listener was updated.
         */
        public boolean updated = false;

        /**
         * The method that is updated when an event is dispatched.
         */
        public void update()
        {
            updated = true;
        }
    }

    /**
     * Triggers a path of logic where a super class must be used instead.
     */
    private class SuperClassSpecific extends ClassSpecific
    {
    }
}
