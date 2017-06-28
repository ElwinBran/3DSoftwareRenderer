
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.matrix.Matrix4fUtilities;
import main.java.models.threedee.objects.RenderableObject;

/**
 * 
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public abstract class AbstractRenderer implements Renderer
{
    /**
     * Renders the {@code objects} on a new {@see javafx.scene.image.Image}.
     * It simply calls the other {@link main.java.models.renderers.Renderer#render(java.util.List, main.java.models.camera.CameraInterface, javafx.scene.image.PixelWriter, javafx.geometry.Point2D)  render method}.
     * 
     * @param objects the object that the camera 'thinks' are in view, and need to be rendered.
     * @param myCamera the camera that called the render method; provides access to the whole scene and projection/screen matrix.
     * @return the rendered {@code objects} on an {@code Image}.
     */
    @Override
    public Image render(List<RenderableObject> objects, CameraInterface myCamera)
    {
        final float width = Matrix4fUtilities.getScreenWidth(myCamera.getSceenSpaceMatrix());
        final float height = Matrix4fUtilities.getScreenHeight(myCamera.getSceenSpaceMatrix());
        WritableImage image = new WritableImage((int)width, (int)height);
        render(objects, myCamera, image.getPixelWriter(), new Point2D(0,0));
        return image;
    }
}
