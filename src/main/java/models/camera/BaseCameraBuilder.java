package main.java.models.camera;

import main.java.models.camera.builderinterfaces.SceneSetter;
import main.java.models.camera.builderinterfaces.TransformSetter;
import main.java.models.camera.builderinterfaces.TypeSetter;
import main.java.models.camera.builderinterfaces.ViewVolumeCullerSetter;
import main.java.models.camera.builderinterfaces.ViewVolumeSetter;
import main.java.models.collision.BoundingVolume;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Transform;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * Builds a {@code AbstractCamera}.
 * 
 * @author Elwin Slokker
 * @version 0.2
 * @param <C> The type of the camera.
 * @param <L> The interface type that the builder will be casted to after the 
 * last method (so a class extending this one may complete the building).
 */
public class BaseCameraBuilder<C extends AbstractCamera, L> implements  SceneSetter<L>,
        TypeSetter<L>, TransformSetter<L>, ViewVolumeSetter<L>, ViewVolumeCullerSetter<L>
{
    protected C camera;
    
    boolean isUsable = false;
    ViewVolumeCullInterface viewVolumeCuller;
    RenderableScene containerScene;
    CameraType type;
    BoundingVolume boundingViewVolume;
    Transform transform;
    
    protected BaseCameraBuilder()
    {
    }
    /**
     * Sets the scene that contains the camera. Followed by {@link setType}.
     * 
     * @param scene
     * @return 
     */
    @Override
    public TypeSetter<L> setScene(RenderableScene scene)
    {
        //getCamera().setScene(scene);
        return this;
    }

    @Override
    public TransformSetter<L> setType(CameraType type)
    {
        //getCamera().setType(type);
        return this;
    }

    @Override
    public ViewVolumeSetter<L> setTransfrom(Transform transform)
    {
        //getCamera().setTransform(transform);
        return this;
    }

    @Override
    public ViewVolumeCullerSetter<L> setViewVolume(BoundingVolume volume)
    {
        //getCamera().setViewBoundingVolume(volume);
        return this;
    }

    
    @Override
    public L setViewVolumeCull(ViewVolumeCullInterface culler)
    {
        //getCamera().setViewVolumeCuller(culler);
        return (L)this;
    }

}
