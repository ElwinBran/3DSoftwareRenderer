
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * A ray tracer with anti-aliasing and a kind of texture filter.
 * 
 * How it works:
 * When a ray hits the plane of a triangle, you need to calculate the barycentric
 * coordinates of the triangle to determine if the ray is inside (and they can be used for maps afterwards).
 * Instead of simply testing the ray against the coordinates, it is tested against a range.
 * Based on the distance, the pixel is made more or less transparent. 
 * (of course the ray will need to be extended and calculate a point behind a transparent pixel)
 * By converting the coordinate using the triangle area and the corner angles one can achieve the same distance for every triangle.
 * 
 * This should also allow for some texture filtering, although mipmaps might be needed then as well.
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class AARayTracer extends RayTracer
{
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

    @Override
    public void prepareForNewFrame()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
