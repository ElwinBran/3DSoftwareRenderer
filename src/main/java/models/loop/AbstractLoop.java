
package main.java.models.loop;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import main.java.models.scene.RenderableScene;
import main.java.view.DisplayInterface;

/**
 *
 * @author Elwin Slokker
 */
public abstract class AbstractLoop extends AnimationTimer implements LoopInterface
{
    /**
     * 
     */
    private long startNanoTime;
    private final List<RenderableScene> scenes = new ArrayList<>();
    private DisplayInterface display;
    
    public AbstractLoop(DisplayInterface initialDisplay)
    {
        this.display = initialDisplay;
    }
    @Override
    public void addScene(RenderableScene scene)
    {
        this.scenes.add(scene);
    }
    protected List<RenderableScene> getScenes()
    {
        return this.scenes;
    }
    @Override
    public void setDisplay(DisplayInterface newDisplay)
    {
        this.display = newDisplay;
    }
    /**
     * 
     */
    @Override
    public void start()
    {
        this.startNanoTime = System.nanoTime();
        super.start();
    }
    public long getStartTime()
    {
        return this.startNanoTime;
    }
}
