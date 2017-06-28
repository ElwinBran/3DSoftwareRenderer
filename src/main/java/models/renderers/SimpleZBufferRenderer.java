
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * A simple renderer that employs the z-buffer technique.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleZBufferRenderer extends AbstractZBufferAlgorithm
{
    public SimpleZBufferRenderer(short width, short height)
    {
        super(width, height);
    }
    @Override
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Image render(List<RenderableObject> objects, CameraInterface myCamera)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
