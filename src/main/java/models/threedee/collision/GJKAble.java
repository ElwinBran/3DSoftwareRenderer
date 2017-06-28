
package main.java.models.threedee.collision;

import main.java.models.threedee.Vector4f;

/**
 * Any convex 3D object that wants to collide by using GJK algorithm must implement this interface.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface GJKAble
{
    /**
     * Should always return a point in the shape that is the furthest point in the shape along the given direction.
     * 
     * @param direction The given direction to look in.
     * @return A point on the edge of the shape.
     */
    public Vector4f furthestPointFromDirection(final Vector4f direction);
}
