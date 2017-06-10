
package main.java.models.collision;

import main.java.models.Vector4f;
import main.java.models.newmodels.Positionable;

/**
 * A BoundingVolume is a representation of any 3D shape that is able to collide 
 * with other shapes.
 * It is only meant to be extended by objects that represent specific simple shapes
 * meant as bounding volumes (spheres, boxes etc.).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public abstract class BoundingVolume implements Collidable, Positionable
{
    /**
     * The place of the BoundingVolume in the world/scene.
     */
    private Vector4f position;
    /**
     * 
     * @param position 
     * @since 0.1
     */
    public BoundingVolume(Vector4f position)
    {
        this.position = position;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Vector4f furthestPointFromDirection(Vector4f direction)
    {
        return getFurthestPointFromDirection(direction).add(this.position);
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
     * @since 0.1
     */
    public abstract Vector4f getFurthestPointFromDirection(Vector4f direction);
    /**
     * {@inheritDoc}
     * @since 0.1
     */
    @Override
    public void setPosition(Vector4f position)
    {
        this.position = position;
    }
    /**
     * {@inheritDoc}
     * @since 0.1
     */
    @Override
    public Vector4f getPosition()
    {
        return this.position;
    }
}
