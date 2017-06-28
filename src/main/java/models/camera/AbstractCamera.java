
package main.java.models.camera;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.threedee.collision.BoundingVolumeInterface;
import main.java.models.threedee.matrix.Matrix4f;
import main.java.models.renderers.Renderer;
import main.java.models.scene.RenderableScene;
import main.java.models.threedee.Transform;
import main.java.models.threedee.Vector4f;
import main.java.models.viewvolumeculling.ViewVolumeCullInterface;

/**
 * A camera in 3D space for rendering.
 * This abstraction provides the basic actions for a camera, like making a view 
 * or setting the view frustum culler. 
 * 
 * @author Elwin Slokker
 * @version 0.7
 */
public abstract class AbstractCamera implements CameraInterface
{
    /**
     * Holds the position of the 'eye' of the camera.
     */
    private Vector4f eyePosition;
    /**
     * The object that captures the scene in a 2D representation.
     */
    private Renderer renderer;
    /**
     * 
     * @param newRenderer 
     */
    protected void setRenderer(Renderer newRenderer)
    {
        this.renderer = newRenderer;
    }
    /**
     * 
     */
    private ViewVolumeCullInterface viewVolumeCuller;
    /**
     * Effectively final by accessors.
     */
    private final RenderableScene containerScene;
    /**
     * 
     */
    private CameraType type;
    /**
     * The volume the camera uses to cull.
     */
    private BoundingVolumeInterface boundingViewVolume;
    /**
     * 
     */
    private Transform transform;
    /**
     * Stores the projection matrix.
     */
    private Matrix4f projectionMatrix;
    /**
     * Stores the screen space matrix.
     */
    private Matrix4f screenMatrix;
    /**
     * {@inheritDoc }
     */
    @Override
    public Vector4f getEyePosition()
    {
        return this.eyePosition;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public void setEyePosition(Vector4f position)
    {
        this.eyePosition = position;
    }
    /**
     * {@inheritDoc }
     */
    @Override 
    public RenderableScene getScene()
    {
        return this.containerScene;
    }
    protected void setScene(RenderableScene scene)
    {
        
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public void setViewVolumeCuller(ViewVolumeCullInterface viewVolumeCuller)
    {
        this.viewVolumeCuller = viewVolumeCuller;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public ViewVolumeCullInterface getViewVolumeCuller()
    {
        return this.viewVolumeCuller;
    }
    
    /**
     * {@inheritDoc }
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
     */
    @Override
    public AbstractCamera setType(CameraType type)
    {
        return this;
        //TODO really needs some thought and work
        //return ((type != this.type )? new (this.viewFrustumCuller, this.type): this);
    }
    /**
     * 
     * @return 
     */
    @Override
    public Transform getTransform()
    {
        return this.transform;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setTransform(Transform transform)
    {
        this.transform = transform;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public BoundingVolumeInterface getViewBoundingVolume()
    {
        return this.boundingViewVolume;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setViewBoundingVolume(BoundingVolumeInterface volume)
    {
        this.boundingViewVolume = volume;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix4f getProjectionSpaceMatrix()
    {
        return this.projectionMatrix;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Matrix4f getSceenSpaceMatrix()
    {
        return this.screenMatrix;
    }
    /**
     * {@inheritDoc }
     */
    @Override
    public void drawView(PixelWriter pixelWriter, Point2D shift)
    {
        renderer.render(this.viewVolumeCuller.meshesInsideViewFrustum(boundingViewVolume, this.containerScene.getObjects()), this, pixelWriter, shift);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image makeView()
    {
        //SimpleCameraBuilder.startBuild(null).setScene(null).setType(null).setTransfrom(null).setViewVolume(null).setViewVolumeCull(null).
        //draw things from the scene
        return renderer.render(this.viewVolumeCuller.meshesInsideViewFrustum(boundingViewVolume, this.containerScene.getObjects()), this);
    }
    
    /**
     * Should only be called through {@code super(builder)}.
     * 
     * @param builder a builder containing all attributes (missing one already crashes the program).
     */
    public AbstractCamera(final BaseCameraBuilder builder)
    {
        this.containerScene = builder.containerScene;
        this.transform = builder.transform;
        this.type = builder.type;
        this.boundingViewVolume = builder.boundingViewVolume;
        this.viewVolumeCuller = builder.viewVolumeCuller;
        this.eyePosition = builder.eyePosition;
    }
}
