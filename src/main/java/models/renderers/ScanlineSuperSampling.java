
package main.java.models.renderers;

import java.util.List;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import main.java.models.camera.CameraInterface;
import main.java.models.threedee.matrix.Matrix4fUtilities;
import main.java.models.threedee.objects.RenderableObject;
import main.java.util.ColorUtil;

/**
 * A scanline renderer that uses super sampling (FSAA).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public class ScanlineSuperSampling extends ScanlineAlgorithm
{
    private byte samplingAmount;
    private int[][] screenBuffer;
    
    /**
     * 
     * @param samplingAmount must be > 2.
     */
    public ScanlineSuperSampling(byte samplingAmount)
    {
        if(samplingAmount < 2)
        {
            throw new IllegalArgumentException("Sampling must be greater than 1");
        }
        //screenBuffer = new int[width * samplingAmount][samplingAmount];
        this.samplingAmount = samplingAmount;
    }

    @Override
    public void render(List<RenderableObject> objects, CameraInterface myCamera, PixelWriter writer, Point2D shift)
    {
        final float width = Matrix4fUtilities.getScreenWidth(myCamera.getSceenSpaceMatrix());
        final float height = Matrix4fUtilities.getScreenHeight(myCamera.getSceenSpaceMatrix());
        screenBuffer = new int[(int)width * samplingAmount][samplingAmount];
        
    }

    @Override
    public Image render(List<RenderableObject> objects, CameraInterface myCamera)
    {
        final float width = Matrix4fUtilities.getScreenWidth(myCamera.getSceenSpaceMatrix());
        final float height = Matrix4fUtilities.getScreenHeight(myCamera.getSceenSpaceMatrix());
        WritableImage image = new WritableImage((int)width, (int)height);
        render(objects, myCamera, image.getPixelWriter(), new Point2D(0,0));
        return image;
    }

    @Override
    public void prepareForNewFrame()
    {
        //buffer is automatically overwritten.
    }
    /**
     * Draws the buffer to the {@code writer} by combining color values.
     * 
     * @param scanline the current scanline index.
     * @param writer the place where pixels should be written to.
     * @param shift possible shift of writing.
     */
    private void sample(int scanline, PixelWriter writer, Point2D shift)
    {
        int red;
        int green;
        int blue;
        for(int i = 0; i < this.screenBuffer[0].length; i += samplingAmount)
        {
            red = 0;
            green = 0;
            blue = 0;
            for(int x = i; x < samplingAmount + i; x++)
            {
                for(int y = 0; y < samplingAmount; y++)
                {
                    //TODO not done.
                    red += this.screenBuffer[x][y];
                    green += this.screenBuffer[x][y];
                    blue += this.screenBuffer[x][y];
                }
            }
            writer.setArgb(i + (int)shift.getX(), scanline + (int)shift.getY(), ColorUtil.getRgb(red / samplingAmount,
                    green / samplingAmount,
                    blue / samplingAmount));
        }
    }
}
