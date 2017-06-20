
package main.java.models.camera;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.threedee.Transform;
import main.java.models.collision.BoundingVolume;
import main.java.models.matrix.Matrix4f;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Transformable;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * Defines basic behaviour of a 3D camera.
 * It can be told how to 'look' at things (through viewing types and algorithms)
 * and to 'look' at them.
 * 
 * @author Elwin Slokker
 * @version 0.4
 */
public interface CameraInterface extends Transformable
{
    /**
     * @return whether this camera is usable or not.
     */
    public boolean isUsable();
    /**
     * Allows to change the usability.
     * 
     * @param isUsable
     */
    @Deprecated
    public void setUsable(boolean isUsable);
    /**
     * @return the scene that contains this camera.
     */
    public RenderableScene getScene();
    /**
     * @return the type of camera (perspective, orthogonal and maybe more in later versions).
     */
    public CameraType getType();
    /**
     * 
     * @param type
     * @return 
     */
    public CameraInterface setType(CameraType type);
    /**
     * {@inheritDoc}
     */
    @Override
    public Transform getTransform();
    /**
     * Set the position and rotation of the camera.
     * Scale might be used for the view of the camera, depending on the used implementations.
     * 
     * @param transform desired transformation.
     */
    @Override
    public void setTransform(Transform transform);
    /**
     * @return the projection matrix this camera uses.
     */
    public Matrix4f getProjectionSpaceMatrix();
    /**
     * @return the screen matrix this camera uses.
     */
    public Matrix4f getSceenSpaceMatrix();
    /**
     * 
     * @param viewVolumeCuller
     */
    public void setViewVolumeCuller(ViewVolumeCullInterface viewVolumeCuller);
    /**
     * 
     * @return 
     */
    public ViewVolumeCullInterface getViewVolumeCuller();
    /**
     * @return the {@code BoundingVolume} assigned to this camera (what it uses 
     * to cull).
     */
    public BoundingVolume getViewBoundingVolume();
    /**
     * Sets the volume the camera uses to cull.
     * 
     * @param volume a new {@code BoundingVolume}.
     */
    public void setViewBoundingVolume(BoundingVolume volume);
    /**
     * The camera makes a 'picture' of what it is 'seeing' and draws it with the
     * {@code pixelWriter}.
     * TODO might become obsolete depending on later choices.
     * 
     * @param pixelWriter the writer the camera should use to output the image.
     */
    public void drawView(PixelWriter pixelWriter);
    /**
     * The camera makes a 'picture' of what it is 'seeing' and draws it on an 
     * {@code Image}, which it returns.
     * TODO might become obsolete depending on later choices.
     * 
     * @return what the camera 'sees' in the scene it is in.
     */
    public Image makeView();
}
