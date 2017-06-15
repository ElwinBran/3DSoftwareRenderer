
package main.java.models.loop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.animation.AnimationTimer;
import main.java.models.scene.RenderableScene;
import main.java.view.DisplayInterface;

/**
 * 
 * @author Elwin Slokker
 * @version 0.2
 */
public abstract class AbstractLoop extends AnimationTimer implements LoopInterface
{
    /**
     * 
     */
    private long startNanoTime;
    /**
     * 
     */
    private final List<RenderableScene> scenes = new ArrayList<>();
    /**
     * 
     */
    private DisplayInterface display;
    /**
     * 
     * @param initialDisplay 
     */
    public AbstractLoop(DisplayInterface initialDisplay)
    {
        this.display = initialDisplay;
    }
    /**
     * 
     * @param scene 
     */
    @Override
    public void addScene(RenderableScene scene)
    {
        this.scenes.add(scene);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<RenderableScene> getScenes()
    {
        return Collections.unmodifiableList(this.scenes);
    }
    /**
     * {@inheritDoc}
     */
    @Deprecated
    @Override
    public void removeScene(RenderableScene scene)
    {
        throw new UnsupportedOperationException("Deprecated.");
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setDisplay(DisplayInterface newDisplay)
    {
        this.display = newDisplay;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DisplayInterface getDisplay()
    {
        return this.display;
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
