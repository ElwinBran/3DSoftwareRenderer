
package main.java.models.camera;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.collision.BoundingVolume;
import main.java.models.scene.RenderableScene;

/**
 * Defines basic behaviour of a 3D camera.
 * It can be told how to 'look' at things (through viewing types and algorithms)
 * and to 'look' at them.
 * 
 * @author Elwin Slokker
 * @version 0.4
 */
public interface CameraInterface
{
    /**
     * @return the scene that contains this camera.
     * @since 0.3
     */
    public RenderableScene getScene();
    /**
     * @return the type of camera (perspective, orthogonal and maybe more in later versions).
     * @since 0.1
     */
    public CameraType getType();
    /**
     * 
     * @param type
     * @return 
     * @since 0.1
     */
    public CameraInterface setType(CameraType type);
    /**
     * 
     * @param viewVolumeCuller
     * @since 0.1
     */
    public void setViewVolumeCuller(ViewVolumeCullInterface viewVolumeCuller);
    /**
     * 
     * @return 
     * @since 0.1
     */
    public ViewVolumeCullInterface getViewVolumeCuller();
    /**
     * @return the {@code BoundingVolume} assigned to this camera (what it uses 
     * to cull).
     * @since 0.4
     */
    public BoundingVolume getViewBoundingVolume();
    /**
     * Sets the volume the camera uses to cull.
     * 
     * @param volume a new {@code BoundingVolume}
     * @since 0.4
     */
    public void setViewBoundingVolume(BoundingVolume volume);
    /**
     * The camera makes a 'picture' of what it is 'seeing' and draws it with the
     * {@code pixelWriter}.
     * TODO might become obsolete depending on later choices.
     * 
     * @param pixelWriter the writer the camera should use to output the image.
     * @since 0.2
     */
    public void drawView(PixelWriter pixelWriter);
    /**
     * The camera makes a 'picture' of what it is 'seeing' and draws it on an 
     * {@code Image}, which it returns.
     * TODO might become obsolete depending on later choices.
     * 
     * @return what the camera 'sees' in the scene it is in.
     * @since 0.2
     */
    public Image makeView();
}
