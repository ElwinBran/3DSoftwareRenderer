
package main.java.models.camera.builderinterfaces;

import main.java.models.camera.CameraType;


/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public interface TypeSetter<L>
{
    public TransformSetter<L> setType(CameraType type);
}
