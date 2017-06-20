
package main.java.models.scene;

import java.util.List;
import main.java.models.camera.CameraInterface;
import main.java.models.lighting.LightInterface;
import main.java.models.newmodels.RenderableObject;

/**
 * A 3D scene that has cameras, objects and lighting.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface RenderableScene extends List<CameraInterface>
{
    /**
     * @return the list of objects (objects are not camera's and not lights) that this scene contains.
     */
    public List<RenderableObject> getObjects();
    /**
     * @return the list of light-sources in this scene.s
     */
    public List<LightInterface> getLights();
}
