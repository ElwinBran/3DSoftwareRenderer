
package main.java.view;

import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;

/**
 *
 * @author Elwin Slokker
 */
public class Display extends Parent
{
    private final Canvas canvas;
    private final int width;
    private final int height;
    
    public Display(final int  width, final int height)
    {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas((double) width, (double) height);
        this.getChildren().add(this.canvas);
    }
}
