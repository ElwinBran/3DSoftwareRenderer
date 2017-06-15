
package main.java.models.renderers;

import java.util.List;
import java.util.concurrent.ExecutorService;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.Polygon;
import main.java.util.ThreadProvider;
import main.java.models.newmodels.RenderableObject;

/**
 * Renders a 3D scene in parallel using Z-Buffer algorithm.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public class ParallelZBuffer extends AbstractZBufferAlgorithm
{
    /**
     * The provided threads are used to compute in parallel.
     */
    private ThreadProvider threadProvider;
    /**
     * Should be called when done with this renderer.
     */
    public void closeThreads()
    {
        this.threadProvider.closeThreads();
    }
    public ParallelZBuffer(short width, short height)
    {
        super(width, height);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        for(RenderableObject object : objects)
        {
            for(Polygon polygon : object.getFacesInScreenSpace(myCamera.getProjectionSpaceMatrix(), myCamera.getSceenSpaceMatrix()))
            {
                render(polygon, myCamera, writer, shift);
            }
        }
    }

    public void render(Polygon polygon, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        //add the shift to the polygon.
        //then pass on?
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Image render(List<RenderableObject> objects, CameraInterface myCamera)
    {
        WritableImage render;
        //= new WritableImage();
        render = null;
        for(RenderableObject object : objects)
        {
            for(Polygon polygon : object.getFacesInScreenSpace(myCamera.getProjectionSpaceMatrix(), myCamera.getSceenSpaceMatrix()))
            {
                render(polygon, myCamera, render.getPixelWriter());
            }
        }
        return render;
    }
    /**
     * This method processes individual polygons.
     * 
     * @param polygon that is transformed to screen space.
     * @param myCamera the camera that uses this {@see main.java.models.renderers.Renderer}.
     * @param writer an access point to write the pixels to.
     */
    public void render(Polygon polygon, CameraInterface myCamera, PixelWriter writer)
    {
        //sorting step
        //split in multiple threads?
            //thread process individual scanlines?
        
    }
}
