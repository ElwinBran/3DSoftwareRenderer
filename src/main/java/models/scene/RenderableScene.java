
package main.java.models.scene;

import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.models.camera.CameraInterface;
import main.java.models.lighting.LightInterface;
import main.java.models.threedee.objects.RenderableObject;
import main.java.models.threedee.Vector4f;

/**
 * A 3D scene that has cameras, objects, lighting and a background.
 * 
 * @author Elwin Slokker
 * @version 0.4
 */
public interface RenderableScene
{
    /**
     * @return the list of objects (objects are not camera's and not lights) that this scene contains.
     */
    public List<RenderableObject> getObjects();
    /**
     * @return the list of light-sources in this scene.s
     */
    public List<LightInterface> getLights();
    /**
     * 
     * @param index
     * @return 
     */
    public CameraInterface getCamera(int index);
    /**
     * 
     * @param camera 
     * @return  
     */
    public boolean addCamera(CameraInterface camera);
    /**
     * 
     * @param index
     * @return 
     */
    public CameraInterface removeCamera(int index);
    /**
     * 
     * @param camera
     * @return 
     */
    public boolean removeCamera(CameraInterface camera);
    /**
     * @return get the background image associated with this scene, if any.
     */
    public Image getBackground();
    /**
     * 
     * @param background 
     */
    public void setBackground(Image background);
    /**
     * Get the color of the background that lies in the given {@code direction}.
     * 
     * @param position the position of the viewer.
     * @param direction the direction that is being looked in.
     * @return a single color.
     */
    public Color getBackgroundColor(Vector4f position, Vector4f direction);
}
