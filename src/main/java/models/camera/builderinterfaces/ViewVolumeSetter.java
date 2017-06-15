
package main.java.models.camera.builderinterfaces;

import main.java.models.collision.BoundingVolume;

/**
 *
 * @author Elwin Slokker
 */
public interface ViewVolumeSetter<L>
{
    public ViewVolumeCullerSetter<L> setViewVolume(BoundingVolume volume);
}
