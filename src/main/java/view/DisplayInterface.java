
package main.java.view;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;

/**
 * Defines the basic display behaviour.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface DisplayInterface
{
    /**
     * @return the width of the display.
     */
    public int getWidth();
    /**
     * @return the height of the display.
     */
    public int getHeight();
    /**
     * @return a writer object that allows to change pixels on the display.
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
