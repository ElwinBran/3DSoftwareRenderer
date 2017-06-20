
package main.java.models.lighting;

import javafx.beans.property.Property;
import javafx.scene.paint.Color;
import main.java.models.threedee.Positionable;
import main.java.models.threedee.Vector4f;

/**
 * Defines the abstract behaviour of a light.
 * 
 * TODO I know the getValue thing is confusing, but it simply needs to be a property
 * and I want to be able to bind it.
 * TODO add rotation
 * @author Elwin Slokker
 * @verion 0.2
 */
public interface LightInterface extends Positionable
{
    /**
     * @return the wrapper of the color.
     */
    public Property<Color> getColorProperty();
    /**
     * @param distance the distance from the light.
     * @return a value between 0.0 and 1.0 indicating the light intensity.
     */
    public float getIntensity(float distance);
    /**
     * @return a normalised vector that specifies the direction of the light.
     */
    public Vector4f getDirection();
}
