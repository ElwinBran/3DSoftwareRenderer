
package main.java.models.scene;

import java.util.List;
import main.java.models.camera.CameraInterface;
import main.java.models.newmodels.ObjectInterface;

/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public interface RenderableScene
{
    public void addObject(ObjectInterface object);
    public void removeObject(ObjectInterface object);
    public List<ObjectInterface> getObjects();
    public void addCamera(CameraInterface camera);
    public void addCamera(CameraInterface camera, int index);
    public CameraInterface getCamera();
    public void removeCamera(CameraInterface camera);
}
