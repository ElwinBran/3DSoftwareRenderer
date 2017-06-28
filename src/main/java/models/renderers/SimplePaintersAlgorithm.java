
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.PixelWriter;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * A very simple painters algorithm implementation.
 * Made to be easy understandable (not optimised).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimplePaintersAlgorithm extends AbstractPaintersAlgorithm
{
    @Override
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        
    }

    @Override
    public void prepareForNewFrame()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
