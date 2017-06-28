
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.objects.RenderableObject;

/**
 * Defines the methods every 3D renderer should have.
 * 
 * TODO remove note:
 * I should make a Map of the render actions.
 * The renderer asks the object what type it is (will make an enum for that) 
 * and then uses the map to call correct render method.
 * 
 * "what is your type?" -> "normal" (goes to normal render)
 * "what is your type?" -> "mathematical volume" (goes volume render for spheres and ellipsoids)
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public interface Renderer
{
    /**
     * When determining the color of a pixel, it is important to check the exact center of the pixel.
     * this is the x center value.
     */
    public final static float X_PIXEL_CORRECTION = 0.5f;
    /**
     * When determining the color of a pixel, it is important to check the exact center of the pixel.
     * this is the y center value.
     */
    public final static float Y_PIXEL_CORRECTION = 0.5f;
    /**
     * Renders the {@code objects} directly through the {@code writer}, displaced by the {@code shift}.
     * 
     * @param objects the object that the camera 'thinks' are in view, and need to be rendered.
     * @param myCamera the camera that called the render method; provides access to the whole scene and projection/screen matrix.
     * @param writer the PixelWriter of the screen target.
     * @param shift a 2D point that determines where the origin is.
     */
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift);
    /**
     * Renders the {@code objects} on a new {@code Image}.
     * 
     * @param objects the object that the camera 'thinks' are in view, and need to be rendered.
     * @param myCamera the camera that called the render method; provides access to the whole scene and projection/screen matrix.
     * @return the rendered {@code objects}.
     */
    public Image render(List<RenderableObject> objects, CameraInterface myCamera);
    /**
     * Call this method before drawing a new frame. It makes sure that buffers are cleared.
     * You might think the renderers do this themselves, but the control needs to be passed upwards.
     */
    public void prepareForNewFrame();
}
