
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
public interface RenderableScene
{
    public void addObject(RenderableObject object);
    public void removeObject(RenderableObject object);
    public List<RenderableObject> getObjects();
    public List<LightInterface> getLights();
    public void addCamera(CameraInterface camera);
    public void addCamera(CameraInterface camera, int index);
    public CameraInterface getCamera(int index);
    public void removeCamera(CameraInterface camera);
}
