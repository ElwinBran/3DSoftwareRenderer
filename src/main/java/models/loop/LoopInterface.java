
package main.java.models.loop;

import java.util.List;
import main.java.models.scene.RenderableScene;
import main.java.view.DisplayInterface;

/**
 * Defines the basic behaviour a render loop.
 * 
 * @author Elwin Slokker
 * @version 0.3
 */
public interface LoopInterface
{
    /**
     * Adds the scene to the loop, so it may do something with it (add object, 
     * edit object, render scene etc.).
     * 
     * @param scene 
     */
    public void addScene(RenderableScene scene);
    /**
     * Should remove a scene from the loop to reduce memory usage.
     * 
     * @param scene the scene that you want to remove.
     * @deprecated loops should be able to decide when to 'load' or 'unload' a scene.
     */
    @Deprecated
    public void removeScene(RenderableScene scene);
    /**
     * @return a read-only list of scenes the loop maintains.
     */
    public List<RenderableScene> getScenes();
    /**
     * This allows for changing the display the loop should render to.
     * 
     * @param display 
     */
    public void setDisplay(DisplayInterface display);
    /**
     * @return the display the loop will render to.
     */
    public DisplayInterface getDisplay();
}
