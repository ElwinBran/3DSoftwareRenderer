
package main.java.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
//import javafx.scene.control.Button;
//import javafx.scene.paint.Color;

/**
 * A simple display 
 * @author Elwin Slokker
 * @version 0.1
 */
public class SimpleDisplay extends AbstractDisplay
{
    private final Canvas canvas;
    
    public SimpleDisplay(final int  width, final int height)
    {
        super(width, height);
        this.canvas = new Canvas((double) width, (double) height);
        this.getChildren().add(this.canvas);
        //this.getChildren().add(new Button("click"));
        //this.canvas.getGraphicsContext2D().setStroke(Color.YELLOW);
        //this.canvas.getGraphicsContext2D().fillRect(0, 0, 10, 10);
    }
    /**
     * 
     * @return 
     */
    public Canvas getCanvas()
    {
        return this.canvas;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PixelWriter getPixelWriter()
    {
        return this.canvas.getGraphicsContext2D().getPixelWriter();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawImage(Image image, int x, int y)
    {
        this.canvas.getGraphicsContext2D().drawImage(image, (double) x, (double) y);
    }
}
