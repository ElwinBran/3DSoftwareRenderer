
package main.java.view;

import javafx.scene.Parent;

/**
 * The abstract implementation of {@see main.java.view.DisplayInterface}.
 * This implementation is meant to be immutable (the {@code width} and {@code height} cannot be changed).
 * 
 * @author Elwin Slokker
 * @version 0.1
 */
public abstract class AbstractDisplay extends Parent implements DisplayInterface
{
    private final int width;
    private final int height;
    
    public AbstractDisplay(final int  width, final int height)
    {
        this.width = width;
        this.height = height;
    }
    @Override
    public int getWidth()
    {
        return this.width;
    }
    @Override
    public int getHeight()
    {
        return this.height;
    }
}
