
package main.java.models.camera;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.collision.BoundingVolume;
import main.java.models.scene.RenderableScene;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * A camera in 3D space for rendering.
 * This abstraction provides the basic actions for a camera, like making a view 
 * or setting the view frustum culler. 
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public abstract class AbstractCamera implements CameraInterface
{
    /**
     * 
     */
    private ViewVolumeCullInterface viewVolumeCuller;
    /**
     * 
     */
    private final RenderableScene containerScene;
    /**
     * 
     */
    private final CameraType type;
    /**
     * The volume the camera uses to cull.
     */
    private BoundingVolume boundingViewVolume;
    /**
     * {@inheritDoc }
     * @since 0.2
     */
    @Override 
    public RenderableScene getScene()
    {
        return this.containerScene;
    }
    /**
     * {@inheritDoc }
     * @since 0.1
     */
    @Override
    public void setViewVolumeCuller(ViewVolumeCullInterface viewVolumeCuller)
    {
        this.viewVolumeCuller = viewVolumeCuller;
    }
    /**
     * {@inheritDoc }
     * @since 0.1
     */
    @Override
    public ViewVolumeCullInterface getViewVolumeCuller()
    {
        return this.viewVolumeCuller;
    }
    
    /**
     * {@inheritDoc }
     * @since 0.1
     */
    @Override
    public CameraType getType()
    {
        return this.type;
    }
    /**
     * 
     * @param type
     * @return 
     * @since 0.1
     */
    @Override
    public AbstractCamera setType(CameraType type)
    {
        return this;
        //TODO really needs some thought and work
        //return ((type != this.type )? new (this.viewFrustumCuller, this.type): this);
    }
    /**
     * {@inheritDoc}
     * @since 0.3
     */
    @Override
    public BoundingVolume getViewBoundingVolume()
    {
        return this.boundingViewVolume;
    }
    /**
     * {@inheritDoc}
     * @since 0.3
     */
    @Override
    public void setViewBoundingVolume(BoundingVolume volume)
    {
        this.boundingViewVolume = volume;
    }
    /**
     * {@inheritDoc }
     * @since 0.2
     */
    @Override
    public void drawView(PixelWriter pixelWriter)
    {
        //this.viewVolumeCuller.meshesInsideViewFrustum(boundingViewVolume, this.containerScene.getObjects());
        //this.containerScene.getObjects()
        //draw things from the scene
    }
    /**
     * {@inheritDoc}
     * @since 0.2
     */
    @Override
    public Image makeView()
    { 
        //draw things from the scene
        return null;
    }
    /**
     * Defacto constructor, a builder seems more suitable.
     * 
     * @param scene
     * @param viewVolumeCuller
     * @param type 
     * @param volume 
     * @since 0.2
     */
    public AbstractCamera(final RenderableScene scene, final ViewVolumeCullInterface viewVolumeCuller, final CameraType type, final BoundingVolume volume)
    {
        this.containerScene = scene;
        this.viewVolumeCuller = viewVolumeCuller;
        this.type = type;
        this.boundingViewVolume = volume;
    }
}
