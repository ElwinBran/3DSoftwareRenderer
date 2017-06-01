package main.java.models;


/**
 *
 * @author Elwin Slokker
 */
public abstract class BoundingShape extends Shape3D
{
    public abstract void updateWorldTransform(Transform modelTransformation);
    /**
     * Determines if a point is in the shape.
     * 
     * @param point The point you want to check, but it needs to be in Object Space.
     * @return true if the point lies in the Shape, false otherwise.
     */
    public abstract boolean isInside(Vector4f point);
    
    /**
     * Determines if a point is in the shape, in World Space.
     * 
     * @param point The point you want to check in World Space.
     * @param translation The translation to World Space of the Object.
     * @return true if the point lies in the Shape, false otherwise.
     */
    public abstract boolean isInside(Vector4f point, Vector4f translation);
}
