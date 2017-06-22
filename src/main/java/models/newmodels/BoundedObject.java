
package main.java.models.newmodels;

import main.java.models.collision.BoundingVolume;

/**
 * An object that has a bounding volume.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public interface BoundedObject
{
    public BoundingVolume getBoundingVolume();
}
