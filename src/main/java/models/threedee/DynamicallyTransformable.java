
package main.java.models.threedee;

import javafx.beans.property.Property;

/**
 * Adds the option of binding a {@see main.java.models.threedee.Transform} to a {@see main.java.models.threedee.Transformable}.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface DynamicallyTransformable extends Transformable
{
    /**
     * @return the transformation property.
     */
    public Property<Transform> getTransformProperty();
}
