package main.java.models.camera;

import main.java.models.collision.BoundingVolume;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Transform;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * Builds a {@code AbstractCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.3
 * @param <B>
 */
public class BaseCameraBuilder<B extends BaseCameraBuilder>
{
    
    boolean isUsable = false;
    ViewVolumeCullInterface viewVolumeCuller;
    RenderableScene containerScene;
    CameraType type;
    BoundingVolume boundingViewVolume;
    Transform transform;
    
    protected BaseCameraBuilder()
    {
        
    }
    public B setVVCuller(ViewVolumeCullInterface viewVolumeCuller)
    {
        this.viewVolumeCuller = viewVolumeCuller;
        return (B)this;
    }
    public B setScene(RenderableScene scene)
    {
        this.containerScene = scene;
        return (B)this;
    }
    public B setType(CameraType type)
    {
        this.type = type;
        return (B)this;
    }
    public B setViewVolume(BoundingVolume boundingViewVolume)
    {
        this.boundingViewVolume = boundingViewVolume;
        return (B)this;
    }
    public B setTransform(Transform transform)
    {
        this.transform = transform;
        return (B)this;
    }
}
