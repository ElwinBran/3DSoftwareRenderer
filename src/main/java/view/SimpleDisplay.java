
package main.java.view;

import javafx.scene.canvas.Canvas;
//import javafx.scene.control.Button;
//import javafx.scene.paint.Color;

/**
 *
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleDisplay extends AbstractDisplay
{
    private final Canvas canvas;
    private final int width;
    private final int height;
    
    public SimpleDisplay(final int  width, final int height)
    {
        this.width = width;
        this.height = height;
        this.canvas = new Canvas((double) width, (double) height);
        this.getChildren().add(this.canvas);
        //this.getChildren().add(new Button("click"));
        //this.canvas.getGraphicsContext2D().setStroke(Color.YELLOW);
        //this.canvas.getGraphicsContext2D().fillRect(0, 0, 10, 10);
    }
    /**
     * 
     * @since 0.1
     * @return 
     */
    public Canvas getCanvas()
    {
        return this.canvas;
    }
}
