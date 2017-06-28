
package main.java.models.threedee.collision;

import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;

/**
 * A {@code BoundingVolume} is a representation of any 3D shape that is able to collide 
 * with other shapes.
 * 
 * It is only meant to be extended by objects that represent specific simple shapes
 * meant as bounding volumes (spheres, boxes etc.).
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public abstract class BoundingVolume implements BoundingVolumeInterface
{
    /**
     * The place of the BoundingVolume in the world/scene.
     */
    private Transform transform;
    /**
     * 
     * @param transform
     */
    public BoundingVolume(final Transform transform)
    {
        this.transform = transform;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4f furthestPointFromDirection(final Vector4f direction)
    {
        return getFurthestPointFromDirection(direction).add(this.transform.getPos());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransform(final Transform transform)
    {
        this.transform = transform;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getTransform()
    {
        return this.transform;
    }
    /**
     * Gets the point that is the furthest away from the given direction.
     * This method is not meant for use outside this class, since it returns the
     * value without position translation.
     * NOTE: unsure if this system works.
     * TODO: test.
     * 
     * @param direction
     * @return the point that lies farthest away from the direction when the 
     * BoundingVolume is at (0,0,0) or <0,0,0,1>.
     */
    public abstract Vector4f getFurthestPointFromDirection(final Vector4f direction);
}
