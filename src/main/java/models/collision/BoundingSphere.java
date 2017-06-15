package main.java.models.collision;

import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;


/**
 *
 * @author Elwin Slokker
 */
public class BoundingSphere extends BoundingShape
{
    private float radius;
    private Vector4f location;
    
    public BoundingSphere(float radius)
    {
        this(new Vector4f(0,0,0,0), radius);
    }
    
    public BoundingSphere(Vector4f location, float radius)
    {
        this.radius = radius;
        this.location = location;
    }
    
    @Override
    public void updateWorldTransform(Transform modelTransformation)
    {
        //this.location = modelTransformation.GetTransformation().Transform(location);
        this.location = modelTransformation.getPos();
    }
    @Override
    public Vector4f furthestPointFromDirection(Vector4f direction)
    {
        //remove the division if direction is known to be normalized elsewhere
        Vector4f newguy = direction.multiply(this.radius / direction.length());
        return  newguy.add(this.location);
    }
    @Override
    public boolean isInside(Vector4f point)
    {
        return radius <= Math.sqrt(point.getX() * point.getX()
                + point.getY() * point.getY()
                + point.getZ() * point.getZ());
    }

    @Override
    public boolean isInside(Vector4f point, Vector4f translation)
    {
        Vector4f originPoint = point.subtract(translation);
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
    public Vector4f getLocation()
    {
        return this.location;
    }
    public void setRadius(final Vector4f newLocation)
    {
        this.location = newLocation;
    }
}
