package main.java.models.camera;

import main.java.models.threedee.collision.BoundingVolumeInterface;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * Builds an {@see main.java.models.camera.AbstractCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.4
 * @param <C> the type of the camera that is to be build.
 */
public abstract class BaseCameraBuilder<C extends AbstractCamera>
{
    boolean isUsable = false;
    Vector4f eyePosition;
    ViewVolumeCullInterface viewVolumeCuller;
    RenderableScene containerScene;
    CameraType type;
    BoundingVolumeInterface boundingViewVolume;
    Transform transform;
    
    /**
     * The single protected constructor prevents misuse.
     */
    protected BaseCameraBuilder()
    {
    }
    /**
     * Set the view volume culler method of this camera.
     * 
     * @param viewVolumeCuller a culler object.
     */
    public void setVVCuller(ViewVolumeCullInterface viewVolumeCuller)
    {
        this.viewVolumeCuller = viewVolumeCuller;
    }
    /**
     * Set the scene the camera belongs to.
     * 
     * @param scene where the camera is a part of.
     */
    public void setScene(RenderableScene scene)
    {
        this.containerScene = scene;
    }
    /**
     * Set what kind of camera this is.
     * 
     * @param type 
     */
    public void setType(CameraType type)
    {
        this.type = type;
    }
    /**
     * Set the volume (= 'hitbox') of this camera.
     * It is the hitbox of what the camera 'sees', not its own 'hitbox'.
     * 
     * @param boundingViewVolume a volume, that will be used when culling.
     */
    public void setViewVolume(BoundingVolumeInterface boundingViewVolume)
    {
        this.boundingViewVolume = boundingViewVolume;
    }
    /**
     * Set the transform of the camera (position, rotation and scaling).
     * 
     * @param transform a valid transform.
     */
    public void setTransform(Transform transform)
    {
        this.transform = transform;
    }
    /**
     * Finishes the building process.
     * 
     * @return a camera.
     */
    public abstract C build();
}
