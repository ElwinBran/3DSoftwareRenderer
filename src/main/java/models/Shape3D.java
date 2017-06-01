package main.java.models;

/**
 *
 * @author Elwin Slokker
 */
public abstract class Shape3D
{
    /**
     * Should always return a point in the shape that is the furthest point in the shape along the given direction.
     * @param direction The given direction to look in.
     * @return A Point in the shape.
     */
    public abstract Vector4f furthestPointFromDirection(Vector4f direction);
}
