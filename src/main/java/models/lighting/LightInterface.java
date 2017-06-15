
package main.java.models.lighting;

import javafx.scene.paint.Color;
import main.java.models.threedee.Vector4f;

/**
 * Defines the abstract behaviour of a light.
 * 
 * @author Elwin Slokker
 * @verion 0.1
 */
public interface LightInterface
{
    /**
     * @return the color of the light.
     */
    public Color getColor();
    /**
     * 
     * @param color 
     */
    public void setColor(Color color);
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
