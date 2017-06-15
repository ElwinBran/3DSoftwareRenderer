
package main.java.view;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;

/**
 *
 * @author Elwin Slokker
 * @version 0.2
 */
public interface DisplayInterface
{
    /**
     * 
     * @return 
     */
    public int getWidth();
    /**
     * 
     * @return 
     */
    public int getHeight();
    /**
     * 
     * @return 
     */
    public PixelWriter getPixelWriter();
    /**
     * The image is drawn on the display on the specified location.
     * 
     * @param image 
     * @param x 
     * @param y 
     */
    public void drawImage(Image image, int x, int y);
}
