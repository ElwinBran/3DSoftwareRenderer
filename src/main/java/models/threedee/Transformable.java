
package main.java.models.threedee;

/**
 * Can be translated (moved), rotated and scaled.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface Transformable
{
    /**
     * Set the transform (translation, rotation, scale).
     * @param transform
     */
    public void setTransform(Transform transform);
    /**
     * @return the transform of {@code this} Object.
     */
    public Transform getTransform();
}
