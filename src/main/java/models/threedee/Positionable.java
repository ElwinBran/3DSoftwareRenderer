
package main.java.models.threedee;

import main.java.models.threedee.Vector4f;

/**
 * A positionable can be asked where it currently is in the 3D world.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface Positionable
{
    /**
     * @return the position vector of the object.
     * @version 0.1
     */
    public Vector4f getPosition();
    /**
     * 
     * @param position
     * @version 0.1
     */
    public void setPosition(Vector4f position);
}
