
package main.java.models.loop;

import main.java.models.scene.RenderableScene;
import main.java.view.DisplayInterface;

/**
 * Defines the basic behaviour a render loop.
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public interface LoopInterface
{
    /**
     * Adds the scene to the loop, so it may do something with it (add object, 
     * edit object, render scene etc.).
     * 
     * @param scene 
     * @since 0.1
     */
    public void addScene(RenderableScene scene);
    /**
     * Should remove a scene from the loop to reduce memory usage.
     * 
     * @param scene the scene that you want to remove.
     * @since 0.2
     * @deprecated loops should be able to decide when to 'load' or 'unload' a scene.
     */
    @Deprecated
    public void removeScene(RenderableScene scene);
    /**
     * This allows for changing the display the loop should render to.
     * 
     * @param display 
     * @since 0.1
     */
    public void setDisplay(DisplayInterface display);
}
