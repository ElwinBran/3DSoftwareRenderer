
package main.java.models.newmodels;

import main.java.models.Vector4f;

/**
 * A Positionable can be asked where it currently is in the 3D world.
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
