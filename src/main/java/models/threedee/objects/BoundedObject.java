
package main.java.models.threedee.objects;

import main.java.models.threedee.collision.BoundingVolumeInterface;

/**
 * An object that has a bounding volume.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface BoundedObject
{
    /**
     * @return the 'hitbox' of this object.
     */
    public BoundingVolumeInterface getBoundingVolume();
}
