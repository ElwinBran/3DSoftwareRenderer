
package main.java.models.camera.builderinterfaces;

import main.java.models.threedee.Transform;

/**
 *
 * @author Elwin Slokker
 * @version 0.2
 * @param <L> the linking interface.
 */
public interface TransformSetter<L>
{
    public ViewVolumeSetter<L> setTransfrom(Transform transform);
}
