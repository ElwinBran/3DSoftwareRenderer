package main.java.models.threedee.collision;

import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;


/**
 * A spherical volume.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class BoundingSphere extends BoundingVolume
{
    private float radius;

    public BoundingSphere(Transform transform, float radius)
    {
        super(transform);
        this.radius = radius;
    }
    
    /**
     * {@inheritDoc }
     */
    @Override
    public Vector4f getFurthestPointFromDirection(final Vector4f direction)
    {
        //remove the division if direction is known to be normalized elsewhere (?)
        Vector4f circlePoint = direction.multiply(this.radius / direction.length());
        return  circlePoint;
    }

    public boolean isInside(final Vector4f point)
    {
        Vector4f originPoint = point.subtract(this.getTransform().getPos());
        return radius <= Math.sqrt(originPoint.getX() * originPoint.getX()
                + originPoint.getY() * originPoint.getY()
                + originPoint.getZ() * originPoint.getZ());
    }
    public float getRadius()
    {
        return this.radius;
    }
    public void setRadius(final float newRadius)
    {
        this.radius = newRadius;
    }
}
