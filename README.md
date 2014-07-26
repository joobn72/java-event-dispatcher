Event Dispatcher
================

[![Build Status][]](https://travis-ci.org/kherge/java-event-dispatcher)

A simple event dispatcher.

Usage
-----

```java
import io.herrera.event_dispatcher.*;

/**
 * An example class that uses an event dispatcher.
 */
public class Example
{
    /**
     * Triggers the example event.
     */
    public static void main(String[] args) throws Exception
    {
        Dispatcher   dispatcher = new ListenerDispatcher();
        ExampleEvent event      = new ExampleEvent("Hello, stranger!");

        dispatcher.registerListener(
            "speak",
            new InstanceListener(new ExampleListener(), "changeMessage")
        );

        dispatcher.dispatch("speak", event);

        System.out.println(event.getMessage());
    }

    /**
     * An example event listener.
     */
    public static class ExampleListener
    {
        /**
         * Changes the message that will be spoken.
         *
         * @param event The event we are listening to.
         */
        public void changeMessage(ExampleEvent event)
        {
            event.setMessage("Hello, world!");
        }
    }

    /**
     * An example event class.
     */
    private static class ExampleEvent extends AbstractEvent
    {
        /**
         * The message that will be spoken.
         */
        private String message;

        /**
         * Sets the message that will be spoken.
         */
        public ExampleEvent(String message)
        {
            this.message = message;
        }

        /**
         * Returns the message that will be spoken.
         *
         * @return The message that will be spoken.
         */
        public String getMessage()
        {
            return message;
        }

        /**
         * Sets the message that will be spoken.
         *
         * @param message The message that will be spoken.
         */
        public void setMessage(String message)
        {
            this.message = message;
        }
    }
}
```

Requirements
------------

- Java 1.7+

[Build Status]: https://travis-ci.org/kherge/java-event-dispatcher.svg?branch=master
