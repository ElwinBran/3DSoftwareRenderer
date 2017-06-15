
package main.java.view;

import javafx.scene.Parent;

/**
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
