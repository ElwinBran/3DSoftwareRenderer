
package main.java.models.threedee.collision;

import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;

/**
 * Represents a single collidable point.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class BoundingPoint extends BoundingVolume
{
    /**
     * Constructor using only the location of the point.
     * 
     * @param point the point in 3D space.
     */
    public BoundingPoint(Vector4f point)
    {
        super(new Transform(point));
    }
    /**
     * Full constructor that allows specifying the full transform.
     * Although rotation and scale are useless for this object.
     * 
     * @param transform 
     */
    public BoundingPoint(Transform transform)
    {
        super(transform);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4f getFurthestPointFromDirection(Vector4f direction)
    {
        return this.getTransform().getPos();
    }
    
}
