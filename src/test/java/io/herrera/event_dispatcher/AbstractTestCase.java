package io.herrera.event_dispatcher;

import java.lang.reflect.Field;
import junit.framework.TestCase;

/**
 * Provides additional testing functionality.
 *
 * @author Kevin Herrera
 */
public abstract class AbstractTestCase extends TestCase
{
    /**
     * Finds a property through reflection and returns it.
     *
     * @param object The class instance.
     * @param field  The name of the field.
     */
    public Field findProperty(Object object, String field) throws NoSuchFieldException
    {
        Class<?> reflectClass = object.getClass();
        Field reflectField = null;

        while ((null != reflectClass) && (null == reflectField)) {
            try {
                reflectField = reflectClass.getDeclaredField(field);
            } catch (NoSuchFieldException exception) {
                reflectClass = reflectClass.getSuperclass();
            }
        }

        if (null == reflectField) {
            throw new NoSuchFieldException(field);
        }

        reflectField.setAccessible(true);

        return reflectField;
    }

    /**
     * Sets the value of an inaccessible property.
     *
     * @param object The class instance.
     * @param field  The name of the field.
     * @param value  The new field value.
     */
    public void setValue(Object object, String field, Object value) throws
        IllegalAccessException,
        NoSuchFieldException
    {
        findProperty(object, field).set(object, value);
    }
}
