
package main.java.models.collision;

import main.java.models.threedee.Vector4f;

/**
 * Any 3D object that can collide with other objects should implement this interface.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface Collidable
{
    /**
     * Should always return a point in the shape that is the furthest point in the shape along the given direction.
     * 
     * @param direction The given direction to look in.
     * @return A Point in the shape.
     */
    public Vector4f furthestPointFromDirection(Vector4f direction);
}
