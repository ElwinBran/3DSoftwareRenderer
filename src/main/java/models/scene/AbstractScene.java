
package main.java.models.scene;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import main.java.models.camera.CameraInterface;
import main.java.models.lighting.LightInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * This abstract class only provides a restriction on the constructor, ensuring a scene has at least one camera.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public abstract class AbstractScene implements RenderableScene
{
    /**
     * Ensures scenes have at least a single camera.
     * 
     * @param camera the first camera of the scene.
     */
    public AbstractScene(CameraInterface camera)
    {
        if(!addCamera(camera) || camera == null)
        {
            throw new RuntimeException("Camera could not be added to scene, making scene unusable.");
        }
    }
}
