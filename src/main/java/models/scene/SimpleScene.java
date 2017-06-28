
package main.java.models.scene;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import main.java.models.camera.CameraInterface;
import main.java.models.lighting.LightInterface;
import main.java.models.threedee.objects.RenderableObject;
import main.java.models.threedee.Vector4f;

/**
 * A simple scene implementation.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleScene extends AbstractScene
{
    private Image background;
    private List<RenderableObject> objects = new ArrayList<>();
    private List<LightInterface> lights = new ArrayList<>();
    private List<CameraInterface> cameras = new ArrayList<>();
    public SimpleScene(CameraInterface camera)
    {
        super(camera);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RenderableObject> getObjects()
    {
        return objects;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<LightInterface> getLights()
    {
        return lights;
    }
    @Override
    public CameraInterface getCamera(int index)
    {
        return this.cameras.get(index);
    }

    @Override
    public boolean addCamera(CameraInterface camera)
    {
        return this.cameras.add(camera);
    }

    @Override
    public CameraInterface removeCamera(int index)
    {
        return this.cameras.remove(index);
    }

    @Override
    public boolean removeCamera(CameraInterface camera)
    {
        return this.cameras.remove(camera);
    }

    @Override
    public Image getBackground()
    {
        return this.background;
    }

    @Override
    public void setBackground(Image background)
    {
        this.background = background;
    }

    /**
     * Uses very simple sphere mapping.
     * 
     * @param position
     * @param direction
     * @return 
     */
    @Override
    public Color getBackgroundColor(final Vector4f position, final Vector4f direction)
    {
        final double width = this.background.getWidth();
        final double height = this.background.getHeight();
        final double u = Math.atan2(direction.getX(), direction.getZ()) / (2 / Math.PI) + 0.5;
        final double v = direction.getY() * 0.5 + 0.5;
        return this.background.getPixelReader().getColor((int)(u * width), (int)(v * height));
    }
    
}
